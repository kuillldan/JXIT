package org.lyk.interceptor;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor
{

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception
	{
		//System.out.println("=====afterCompletion=====");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception
	{
		//System.out.println("=====postHandle=====");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception
	{
		HandlerMethod handlerMethod = (HandlerMethod)obj;
		String ruleFieldName = handlerMethod.getMethod().getName()  +"Rule";
		Field validationRuleField = handlerMethod.getBean().getClass().getDeclaredField(ruleFieldName);
		validationRuleField.setAccessible(true);
		String validationRule = (String)validationRuleField.get(handlerMethod.getBean());
		String[] allValidationRule = validationRule.split("\\|");
		for(String eachValidationRule : allValidationRule)
		{
			String expectedFieldName = eachValidationRule.split(":")[0];
			String expectedFieldType = eachValidationRule.split(":")[1];
			
			String actualFieldValue = request.getParameter(expectedFieldName);
			if(StringUtils.isEmpty(actualFieldValue))
			{
				request.getRequestDispatcher("/pages/errors.jsp").forward(request, response);
				return false;
			}
			
			if("int".equalsIgnoreCase(expectedFieldType) && !actualFieldValue.matches("\\d+"))
			{
				request.getRequestDispatcher("/pages/errors.jsp").forward(request, response);
				return false;
			}  
		}
		
		Field mimeField = handlerMethod.getBean().getClass().getDeclaredField("mimeRule");
		if(mimeField != null)
		{
			System.out.println("mimeField:" + mimeField.getName());
			mimeField.setAccessible(true);
			String mimeTypeInString = (String)mimeField.get(handlerMethod.getBean());
			System.out.println(mimeTypeInString);
			if(!StringUtils.isEmpty(mimeTypeInString))
			{ 
				String[] mimeTypesInArray = mimeTypeInString.split("\\|");
				MultipartResolver multipartResolver = new CommonsMultipartResolver();
				if(multipartResolver.isMultipart(request))
				{
					MultipartRequest multipartRequest = (MultipartRequest)request;
					Map<String, MultipartFile> allFiles = multipartRequest.getFileMap();
					if(allFiles.size() > 0)
					{
						Set<Map.Entry<String, MultipartFile>> entrySet = allFiles.entrySet();
						for(Map.Entry<String, MultipartFile> eachFile : entrySet )
						{
							System.out.println("文件类型:" + eachFile.getValue().getContentType());
							if(!mimeTypeInString.contains(eachFile.getValue().getContentType()))
							{
								request.setAttribute("msg", "上传文件不合法");
								request.setAttribute("url", "pages/errors.jsp");
								request.getRequestDispatcher("/pages/common/forward.jsp").forward(request, response);
								return false;
							}
						}
					} 
					else
					{
						request.setAttribute("msg", "未上传文件");
						request.setAttribute("url", "pages/errors.jsp");
						request.getRequestDispatcher("/pages/common/forward.jsp").forward(request, response);
						return false;
					}
				}
				else
				{
					request.setAttribute("msg", "未上传文件");
					request.setAttribute("url", "pages/errors.jsp");
					request.getRequestDispatcher("/pages/common/forward.jsp").forward(request, response);
					return false;
				}
			}
		}
		 
		
		System.out.println("=====preHandle=====");
		return true;
	}

}
