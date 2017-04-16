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
			String status = General.getRequestStatus(request);
			String path = CONST.pageError;

			boolean validationPassed = false;

			if (request.getContentType().contains("multipart/form-data"))
			{
				this.smartUpload = new SmartUpload();
				validationPassed = this.validateRule(status, true);
				System.out.println("验证结果:" + validationPassed);
				System.out.println("错误信息:" + this.errors);
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
			System.out.println("status : " + status);
			Field validationRule = this.getClass().getDeclaredField(status + "Validation");
			validationRule.setAccessible(true);
			String rule = (String) validationRule.get(this);
			boolean validationPassed = true;
			String[] allProperties = rule.split("\\|");
			for (int i = 0; i < allProperties.length; i++)
			{
				String propertyName = allProperties[i];
				Field field = BeanOperator.getField(propertyName, this);
				String fieldType = field.getType().getSimpleName();
				
				if (isEncryed == false)
				{
					if (StringUtils.isEmpty(this.request.getParameter(propertyName)))
					{
						validationPassed = false;
						errors.put(propertyName, propertyName + "不能为空");
					}
					else
					{
						if (CONST.DATATYPE.String.getRealType().equalsIgnoreCase(fieldType))
						{
							//是String 类型直接赋值
						} else if (CONST.DATATYPE.Integer.getRealType().equalsIgnoreCase(fieldType)
								|| CONST.DATATYPE.Int.getRealType().equals(fieldType))
						{
							if (StringUtils.validateRegex(this.value, "\\d+"))
							{
								setter.invoke(this.obj, Integer.parseInt(this.value));
							}
						} else if (CONST.DATATYPE.Double.getRealType().equalsIgnoreCase(fieldType))
						{
							if (StringUtils.validateRegex(this.value, "\\d+(\\.\\d+)?"))
							{
								setter.invoke(this.obj, Double.parseDouble(this.value));
							}

						} else if (CONST.DATATYPE.DATE.getRealType().equalsIgnoreCase(fieldType))
						{
							if (StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2}"))
							{
								setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd").parse(this.value));
							} else if (StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))
							{
								setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.value));
							} else if (StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
							{
								setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(this.value));
							}
						} else if (CONST.DATATYPE.StringArray.getRealType().equalsIgnoreCase(fieldType))
						{
							String[] values = this.arrayValue;
							setter.invoke(this.obj, new Object[] { values });
						} else if (CONST.DATATYPE.IntArray.getRealType().equalsIgnoreCase(fieldType))
						{
							int[] values = new int[this.arrayValue.length];
							for (int i = 0; i < values.length; i++)
							{
								values[i] = Integer.parseInt(this.arrayValue[i]);
							}
							setter.invoke(this.obj, new Object[] { values });
						} else if (CONST.DATATYPE.IntegerArray.getRealType().equalsIgnoreCase(fieldType))
						{
							Integer[] values = new Integer[this.arrayValue.length];
							for (int i = 0; i < this.arrayValue.length; i++)
							{
								values[i] = Integer.parseInt(this.arrayValue[i]);
							}
							setter.invoke(this.obj, new Object[] { values });
						} else if (CONST.DATATYPE.doubleArray.getRealType().equals(fieldType))
						{
							double[] values = new double[this.arrayValue.length];
							for (int i = 0; i < this.arrayValue.length; i++)
							{
								values[i] = Double.parseDouble(this.arrayValue[i]);
							}
							setter.invoke(this.obj, new Object[] { values });
						} else if (CONST.DATATYPE.DoubleArray.getRealType().equals(fieldType))
						{
							Double[] values = new Double[this.arrayValue.length];
							for (int i = 0; i < this.arrayValue.length; i++)
							{
								values[i] = Double.parseDouble(this.arrayValue[i]);
							}
							setter.invoke(this.obj, new Object[] { values });
						} else
						{
							throw new Exception("unsupported data type: " + fieldType);
						}
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
			System.out.println("未找到验证规则.");
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

				beanOperator.handleProperties(this.errors);
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

				beanOperator.handleProperties(this.errors);
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
