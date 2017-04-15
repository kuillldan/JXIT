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
			
			if (request.getContentType().contains("multipart/form-data"))
			{
				this.smartUpload = new SmartUpload();
				this.handleEncrypedHttpData(); 
			} else
			{
				this.handleNormalHttpData();
			}

			status = General.getRequestStatus(request);
			Method m = this.getClass().getMethod(status);
			m.invoke(this);
			
			
		} catch (Exception e)
		{
			General.setSystemError(e);
		}
	}
	
	private boolean validateRule(String status)
	{
		Map<String, String> errors = new HashMap<String, String>();
		try
		{
			Field validationRule = this.getClass().getDeclaredField(status + "Validation");
			validationRule.setAccessible(true);
			String rule = (String)validationRule.get(this);
			boolean validationPassed = false;
			String[] allProperties = rule.split("\\|");
			for(int i =0; i < allProperties.length; i++)
			{
				String propertyName = allProperties[i];
				Field fieldNeedValidation = BeanOperator.getField(propertyName, this);
				String fieldType = fieldNeedValidation.getType().getSimpleName();
				
				if (CONST.DATATYPE.String.getRealType().equalsIgnoreCase(fieldType))
				{
					if(StringUtils.isEmpty(request.getParameter(propertyName)))
					{
						errors.put(propertyName, "字符串字段(" + propertyName + ")不能为空");
					}
				} else if (CONST.DATATYPE.Integer.getRealType().equalsIgnoreCase(fieldType)
						|| CONST.DATATYPE.Int.getRealType().equals(fieldType))
				{
					if (StringUtils.validateRegex(this.value, "\\d+"))
					{
						
					}
				} else if (CONST.DATATYPE.Double.getRealType().equalsIgnoreCase(fieldType))
				{
					if (StringUtils.validateRegex(this.value, "\\d+(\\.\\d+)?"))
					{
						
					}

				} else if (CONST.DATATYPE.DATE.getRealType().equalsIgnoreCase(fieldType))
				{
					if (StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2}"))
					{
						
					} else if (StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))
					{
						
					} else if (StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
					{
						
					}
				} else if (CONST.DATATYPE.StringArray.getRealType().equalsIgnoreCase(fieldType))
				{
					String[] values = this.arrayValue;
					
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
			
			return validationPassed;
			
		} catch (NoSuchFieldException | SecurityException e)
		{
			//没有找到验证规则
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
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
		}
		else
		{
			return false;
		}
	}

	protected String generateFileName(SmartFile smartFile)
	{
		if(this.isImage(smartFile))
		{
			return UUID.randomUUID().toString() + "." + smartFile.getFileExt();
		}
		return null;
	}

	protected void saveFile(Integer fileIndex, String fileName) throws Exception
	{
		String filePath = super.getServletContext().getRealPath("/photos/" + this.getUploadFolderName() + "/") + fileName;
		System.out.println("[debug] " + filePath);
		File fileUploaded = new File(filePath);
		if(!fileUploaded.getParentFile().exists())
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
			for(int i = 0; i < allFiles.getCount(); i++)
			{
				SmartFile smartFile = allFiles.getFile(i);
				if(this.isImage(smartFile))
				{
					String fileName = this.generateFileName(smartFile);
					this.saveFile(i, fileName);
					System.out.println("第("+i+")个文件已经保存. 文件名: " + fileName + ",文件大小:" + smartFile.getSize());
				}
			}
			
		}catch(Exception e)
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
		if(smartFile.getSize() > 0 && smartFile.getContentType().contains("image"))
		{
			return true;
		}
		else
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
