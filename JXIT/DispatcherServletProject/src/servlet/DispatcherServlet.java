package servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.channels.IllegalSelectorException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartFiles;
import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;

import utils.BeanOperator;
import utils.CONST;
import utils.General;
import utils.StringUtils;

public abstract class DispatcherServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private SmartUpload smartUpload;
	private Map<String, String> errors = new HashMap<String, String>();

	public DispatcherServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		try
		{
			this.request = request;
			this.response = response;
			String status = null;
			String path = CONST.pageError;

			boolean validationPassed = false;

			if (request.getContentType().contains("multipart/form-data"))
			{
				this.smartUpload = new SmartUpload();
				validationPassed = this.validateRule(status, true);
				if(validationPassed)
				{
					//数据验证通过
					this.handleEncrypedHttpData();
				}
				else
				{
					//数据验证未通过
					request.setAttribute("errors", this.errors);
					request.getRequestDispatcher(path).forward(request, response);
				}

			} else
			{
				validationPassed = this.validateRule(status, false);
				if(validationPassed)
				{
					this.handleNormalHttpData();
				}
				else
				{
					request.setAttribute("errors", this.errors);
					request.getRequestDispatcher(path).forward(request, response);
				}
			}

			status = General.getRequestStatus(request);
			Method m = this.getClass().getMethod(status);
			m.invoke(this);

		} catch (Exception e)
		{
			General.setSystemError(e);
		}
	}

	private boolean validateRule(String status, boolean isEncryed) throws Exception
	{
		try
		{
			Field validationRule = this.getClass().getDeclaredField(status + "Validation");
			validationRule.setAccessible(true);
			String rule = (String) validationRule.get(this);
			boolean validationPassed = true;
			String[] allProperties = rule.split("\\|");
			for (int i = 0; i < allProperties.length; i++)
			{
				String propertyName = allProperties[i];
				if (isEncryed == false)
				{
					if (StringUtils.isEmpty(this.request.getParameter(propertyName)))
					{
						validationPassed = false;
						errors.put(propertyName, propertyName + "不能为空");
					}
				} else
				{
					if (StringUtils.isEmpty(this.smartUpload.getRequest().getParameter(propertyName)))
					{
						validationPassed = false;
						errors.put(propertyName, propertyName + "不能为空");
					} 
				}
			}

			return validationPassed;

		} catch (NoSuchFieldException | SecurityException e)
		{
			return true;
		} catch (Exception e)
		{
			throw e;
		}
	}

	private void handleNormalHttpData() throws Exception
	{
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements())
		{
			String propertyNames = parameterNames.nextElement();
			Field propertyField = BeanOperator.getField(propertyNames, this);

			if (propertyNames.contains("."))
			{
				// 自定义bean属性
				BeanOperator beanOperator = null;
				if (!propertyField.getType().getSimpleName().contains("[]"))
				{
					// 普通属性
					beanOperator = new BeanOperator(this, propertyNames, request.getParameter(propertyNames));
				} else
				{
					// 数组属性
					beanOperator = new BeanOperator(this, propertyNames, request.getParameterValues(propertyNames));
				}

				beanOperator.handleProperties();
			} else
			{
				// 一般其它属性，此处不处理
				// System.out.println();
			}

		}
	}

	private void handleEncrypedHttpData() throws Exception
	{
		this.smartUpload.initialize(super.getServletConfig(), this.request, response);
		this.smartUpload.upload();
		SmartRequest smartRequest = this.smartUpload.getRequest();

		Enumeration<String> parameterNames = smartRequest.getParameterNames();
		while (parameterNames.hasMoreElements())
		{
			String propertyNames = parameterNames.nextElement();
			Field propertyField = BeanOperator.getField(propertyNames, this);

			if (propertyNames.contains("."))
			{
				// 自定义bean属性
				BeanOperator beanOperator = null;
				if (!propertyField.getType().getSimpleName().contains("[]"))
				{
					// 普通属性
					beanOperator = new BeanOperator(this, propertyNames, smartRequest.getParameter(propertyNames));
				} else
				{
					// 数组属性
					beanOperator = new BeanOperator(this, propertyNames, smartRequest.getParameterValues(propertyNames));
				}

				beanOperator.handleProperties();
			} else
			{
				// 一般其它属性，此处不处理
				// System.out.println();
			}

		}
	}

	protected boolean isUpload() throws Exception
	{
		if (this.smartUpload.getFiles().getCount() > 0 && this.smartUpload.getFiles().getSize() > 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

	protected String generateFileName(SmartFile smartFile)
	{
		if (this.isImage(smartFile))
		{
			return UUID.randomUUID().toString() + "." + smartFile.getFileExt();
		}
		return null;
	}

	protected void saveFile(Integer fileIndex, String fileName) throws Exception
	{
		String filePath = super.getServletContext().getRealPath("/photos/" + this.getUploadFolderName() + "/")
				+ fileName;
		System.out.println("[debug] " + filePath);
		File fileUploaded = new File(filePath);
		if (!fileUploaded.getParentFile().exists())
		{
			fileUploaded.getParentFile().mkdirs();
		}
		this.smartUpload.getFiles().getFile(fileIndex).saveAs(filePath);
	}

	protected void saveAllFiles()
	{
		try
		{
			SmartFiles allFiles = this.smartUpload.getFiles();
			for (int i = 0; i < allFiles.getCount(); i++)
			{
				SmartFile smartFile = allFiles.getFile(i);
				if (this.isImage(smartFile))
				{
					String fileName = this.generateFileName(smartFile);
					this.saveFile(i, fileName);
					System.out.println("第(" + i + ")个文件已经保存. 文件名: " + fileName + ",文件大小:" + smartFile.getSize());
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void deleteFile(String fileName)
	{
	}

	protected void deleteFiles(Set<String> allFileNames)
	{
	}

	private boolean isImage(SmartFile smartFile)
	{
		if (smartFile.getSize() > 0 && smartFile.getContentType().contains("image"))
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * 获取操作的分类信息
	 * 
	 * @return
	 */
	protected abstract String getTitle();

	/**
	 * 返回文件保存路径
	 * 
	 * @return
	 */
	protected abstract String getUploadFolderName();

}
