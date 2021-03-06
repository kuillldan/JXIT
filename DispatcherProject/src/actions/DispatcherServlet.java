package actions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartFiles;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import utils.BeanOperator;
import utils.StringUtils;

@SuppressWarnings("serial")
public abstract class DispatcherServlet extends HttpServlet
{

	private ResourceBundle pageResource;
	private ResourceBundle messageResource;
	private SmartUpload smart;

	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		Locale locale = Locale.getDefault();
		this.pageResource = ResourceBundle.getBundle("Pages", locale);
		this.messageResource = ResourceBundle.getBundle("Messages", locale);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		try
		{
			this.smart = null;

			if (this.isEncrypted(request))
			{
				this.smart = new SmartUpload();
				this.smart.initialize(super.getServletConfig(), request, response);
				this.smart.upload();
			}

			String methodName = this.getMethodName(request);
			Map<String, String> errors = this.validateValues(request, methodName);
			if (errors.size() <= 0)
			{
				this.setValuesAutomaticlly(request, response);
				Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,
						HttpServletResponse.class);
				String pageKey = (String) method.invoke(this, request, response);
				request.getRequestDispatcher(this.pageResource.getString(pageKey)).forward(request, response);
			} else
			{
				List<String> errorFields = new ArrayList<String>();
				Iterator<Map.Entry<String, String>> iter = errors.entrySet().iterator();
				while (iter.hasNext())
				{
					errorFields.add(iter.next().getKey());
				}
				request.setAttribute("errorFields", errorFields);
				request.setAttribute("errors", errors);
				request.getRequestDispatcher(this.pageResource.getString("error.page")).forward(request, response);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private String getMethodName(HttpServletRequest request)
	{
		String uri = request.getRequestURI();
		return uri.substring(uri.lastIndexOf("/") + 1);
	}

	protected String setPathAndMessage(HttpServletRequest request, HttpServletResponse response, String path,
			String message)
	{
		request.setAttribute("msg", MessageFormat.format(this.messageResource.getString(message), this.getObjectName()));
		request.setAttribute("url", this.pageResource.getString(path));
		return "forward.page";
	}

	protected abstract String getObjectName();

	public Map<String, String> validateValues(HttpServletRequest request, String status) throws Exception
	{
		Map<String, String> errors = new HashMap<String, String>();
		Field validations = null;
		try
		{
			validations = this.getClass().getDeclaredField(status+"Validation");
		}
		catch(Exception e)
		{
			return errors;
		}
 

		validations.setAccessible(true);
		String validationString = (String) validations.get(this);
		String[] properties = validationString.split("\\|");
		for (int i = 0; i < properties.length; i++)
		{
			String property = properties[i];
			BeanOperator bo = new BeanOperator(this, property);
			bo.handleString();
			Object value = this.getValue(request, bo, property);
			bo.handleString();
			String error = bo.validateValue(value);
			if(!StringUtils.isEmpty(error))
			{
				errors.put(property, error);
			}
		}
		return errors;
	}

	public void setValuesAutomaticlly(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Enumeration<String> parameterNames = this.getParameters(request);

		while (parameterNames.hasMoreElements())
		{
			String parameterName = parameterNames.nextElement();

			if (parameterName.contains("."))
			{
				BeanOperator bo = new BeanOperator(this, parameterName);
				bo.handleString();
				Object value = this.getValue(request, bo, parameterName);

				bo.setValue(value);
			}
		}
	}

	private Object getValue(HttpServletRequest request, BeanOperator bo, String parameterName)
	{
		Object value = null;
		Field lastField = bo.getLastField();

		if (!lastField.getType().getSimpleName().contains("[]"))
		{
			// 非数组
			if (this.isEncrypted(request))
			{
				// 数据已经封装
				value = this.smart.getRequest().getParameter(parameterName);
			} else
			{
				value = request.getParameter(parameterName);
			}
		} else
		{
			// 数组
			if (this.isEncrypted(request))
			{
				// 数据已经封装
				value = this.smart.getRequest().getParameterValues(parameterName);
			} else
			{
				value = request.getParameterValues(parameterName);
			}
		}

		return value;
	}

	private Enumeration<String> getParameters(HttpServletRequest request)
	{
		Enumeration<String> parameterNames = null;
		if (this.isEncrypted(request))
		{
			// 数据已经封装
			parameterNames = this.smart.getRequest().getParameterNames();
		} else
		{
			parameterNames = request.getParameterNames();
		}

		return parameterNames;
	}

	public List<String> saveFiles(HttpServletRequest request, String content) throws IOException, SmartUploadException
	{
		List<String> allFileNames = new ArrayList<String>();
		if (this.isEncrypted(request) && this.smart != null)
		{
			SmartFiles files = this.smart.getFiles();
			for (int i = 0; i < files.getCount(); i++)
			{
				if (files.getFile(i).getSize() > 0 && files.getFile(i).getContentType().contains(content))
				{
					String fileName = UUID.randomUUID().toString() + "." + files.getFile(i).getFileExt();
					String filePath = request.getServletContext()
							.getRealPath("/upload/" + this.getUploadFolder() + "/");
					File file = new File(filePath);
					if (!file.exists())
					{
						file.mkdirs();
					}

					files.getFile(i).saveAs(filePath + "/" + fileName);

					allFileNames.add(fileName);
				} else
				{
					allFileNames.add(null);
				}
			}
		}

		return allFileNames;
	}

	protected String getUploadFolder()
	{
		return "default";
	}

	private boolean isEncrypted(HttpServletRequest request)
	{
		return request.getContentType() != null && request.getContentType().contains("multipart/form-data");
	}
}
