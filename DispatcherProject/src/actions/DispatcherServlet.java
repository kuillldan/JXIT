package actions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
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
			
			if(this.isEncrypted(request))
			{
				this.smart = new SmartUpload();
				this.smart.initialize(super.getServletConfig(), request, response);
				this.smart.upload();
			}
			
			this.setValuesAutomaticlly(request, response);
			String methodName = this.getMethodName(request);
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			String pageKey = (String) method.invoke(this, request, response);
			request.getRequestDispatcher(this.pageResource.getString(pageKey)).forward(request, response);
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

	public void setValuesAutomaticlly(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Enumeration<String> parameterNames = null;
		if(this.isEncrypted(request))
		{
			//数据已经封装 
			parameterNames = this.smart.getRequest().getParameterNames(); 
		}
		else
		{
			parameterNames = request.getParameterNames();
		}
		
		
		while (parameterNames.hasMoreElements())
		{
			String parameterName = parameterNames.nextElement();
			
			if (parameterName.contains("."))
			{
				Object value = null;
				
				BeanOperator bo = new BeanOperator(this, parameterName);
				bo.handleString();
				Field lastField = bo.getLastField();
				 
				if(!lastField.getType().getSimpleName().contains("[]"))
				{
					//非数组
					if(this.isEncrypted(request))
					{
						//数据已经封装 
						value = this.smart.getRequest().getParameter(parameterName);
					}
					else
					{
						value = request.getParameter(parameterName);
					}
				}
				else
				{
					//数组
					if(this.isEncrypted(request))
					{
						//数据已经封装 
						value = this.smart.getRequest().getParameterValues(parameterName);
					}
					else
					{
						value = request.getParameterValues(parameterName);
					}
				}
				
				bo.setValue(value);
			}
		}
	}
	
	public List<String> saveFiles(HttpServletRequest request,String content) throws IOException, SmartUploadException
	{
		List<String> allFileNames = new ArrayList<String>();
		if(this.isEncrypted(request) && this.smart != null)
		{
			SmartFiles files = this.smart.getFiles();
			for(int i = 0; i < files.getCount(); i++)
			{
				if(files.getFile(i).getSize() > 0 && files.getFile(i).getContentType().contains(content))
				{
					String fileName = UUID.randomUUID().toString() + "." + files.getFile(i).getFileExt();
					String filePath = request.getServletContext().getRealPath("/upload/" + this.getUploadFolder() + "/") ;
					File file = new File(filePath);
					if(!file.exists())
					{
						file.mkdirs();
					}
					
					files.getFile(i).saveAs(filePath + "/" + fileName);
					
					allFileNames.add(fileName);
				}
				else
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
		return request.getContentType().contains("multipart/form-data");
	}
}
