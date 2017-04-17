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

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected SmartUpload smartUpload;
	
	protected Integer currentPage ;
	protected Integer lineSize;
	protected String columns ;
	protected String column ;
	protected String keyWord ;
	
	//private Map<String, String> errors = new HashMap<String, String>();

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
			String status = General.getRequestStatus(request);
			String path = CONST.errorPage;

			Map<String, String> errors = null;
			
			if ( request.getContentType() != null && request.getContentType().contains("multipart/form-data"))
			{
				this.smartUpload = new SmartUpload();
				this.smartUpload.initialize(super.getServletConfig(), this.request, response);
				this.smartUpload.upload();
				
				errors = this.validateEncryptedHttpData(); 
				if(errors.size() <= 0)
				{
					// 数据验证通过
					this.handleEncrypedHttpData();
				}
				else
				{
					System.out.println("[debug] 数据验证未通过: " + errors );
					//数据验证未通过
					request.getRequestDispatcher(CONST.errorPage).forward(request, response);
				}

			} else
			{ 
				errors = this.validateNormalHttpData(); 
				if(errors.size() <= 0)
				{
					this.handleNormalHttpData();
				}
				else
				{//验证未通过
					System.out.println("[debug] 数据验证未通过: " + errors );
					request.getRequestDispatcher(CONST.errorPage).forward(request, response);
				}
			}

			status = General.getRequestStatus(request);
			Method m = this.getClass().getMethod(status);
			path = (String)m.invoke(this);
			System.out.println("[debug] path: " + path);
			request.getRequestDispatcher(path).forward(request, response);

		} catch (Exception e)
		{
			General.setSystemError(e);
		}
	}
	
	protected void handleSplit()
	{
		currentPage = 1;
		lineSize = 5;
		columns = this.getColumns();
		column = this.getColumn();
		keyWord = "";
		
		try
		{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e)
		{
		}
		try
		{
			lineSize = Integer.parseInt(request.getParameter("lineSize"));
		} catch (Exception e)
		{
		}
		if (!StringUtils.isEmpty(request.getParameter("column")))
		{
			column = request.getParameter("column");
		}
		if (!StringUtils.isEmpty(request.getParameter("keyWord")))
		{
			keyWord = request.getParameter("keyWord");
		}
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lineSize", lineSize);
		request.setAttribute("columns", columns);
		request.setAttribute("column", column);
		request.setAttribute("keyWord", keyWord);
	}

	private Map<String, String> validateNormalHttpData() throws Exception
	{
		Map<String, String> errors = new HashMap<String, String>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements())
		{
			String propertyNames = parameterNames.nextElement();
			Field propertyField = null;
			try
			{
				propertyField = BeanOperator.getField(propertyNames, this);
			}
			catch(NoSuchFieldException e)
			{
				continue;
			}

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

				beanOperator.validateProperties(errors);
			} else
			{
				// 一般其它属性，此处不处理
				// System.out.println();
			}
		}
		
		return errors;
	}
	
	
	private Map<String, String> validateEncryptedHttpData() throws Exception
	{ 
		Map<String, String> errors = new HashMap<String, String>();
		SmartRequest smartRequest = this.smartUpload.getRequest();
		
		Enumeration<String> parameterNames = smartRequest.getParameterNames();
		while (parameterNames.hasMoreElements())
		{
			String propertyNames = parameterNames.nextElement();
			Field propertyField = null;
			try
			{
				propertyField = BeanOperator.getField(propertyNames, this);
			}
			catch(NoSuchFieldException e)
			{
				continue;
			}
 
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

				beanOperator.validateProperties(errors);
			} else
			{
				// 一般其它属性，此处不处理
				// System.out.println();
			}
		}
		
		return errors;
	}
	
	

	private void handleNormalHttpData() throws Exception
	{
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements())
		{
			String propertyNames = parameterNames.nextElement();
			Field propertyField = null;
			try
			{
				propertyField = BeanOperator.getField(propertyNames, this);
			}
			catch(NoSuchFieldException e)
			{
				continue;
			}

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
		SmartRequest smartRequest = this.smartUpload.getRequest();

		Enumeration<String> parameterNames = smartRequest.getParameterNames();
		while (parameterNames.hasMoreElements())
		{
			String propertyNames = parameterNames.nextElement();
			Field propertyField = null;
			try
			{
				propertyField = BeanOperator.getField(propertyNames, this);
			}
			catch(NoSuchFieldException e)
			{
				continue;
			}

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
	
	/**
	 * 从子类中获取分页中的所有查询列
	 * @return
	 */
	protected abstract String getColumns();
	/**
	 * 从子类中获取默认查询咧
	 * @return
	 */
	protected abstract String getColumn();
}