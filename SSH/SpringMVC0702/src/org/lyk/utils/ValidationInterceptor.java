package org.lyk.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ValidationInterceptor implements HandlerInterceptor
{
	Logger logger = Logger.getLogger(ValidationInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) throws Exception
	{
		System.out.println("【***afterComplition】***" + handler);
		logger.info("【***afterComplition】***" + handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView mav) throws Exception
	{
		System.out.println("【***postHandle***】" + handler.getClass() + ",modelAndView=" + mav.getView());
		this.logger.info("【***postHandle***】" + handler.getClass() + ",modelAndView=" + mav.getView());
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception
	{
		System.out.println("===========================");
		System.out.println(request.getClass().getSimpleName());
		System.out.println("===========================");

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerName = handlerMethod.getBean().getClass().getSimpleName() + "."
				+ handlerMethod.getMethod().getName();

		String parameterRuleKey = handlerName + ".rules";
		String parameterRuleValue = this.getValueFromResources(handlerMethod, parameterRuleKey);

		String fileRuleKey = handlerName + ".mime.rule";
		String fileRuleValue = this.getValueFromResources(handlerMethod, fileRuleKey);
		if (StringUtils.isEmpty(fileRuleValue))
		{
			fileRuleValue = this.getValueFromResources(handlerMethod, "mime.rule");
		}

		Map<String, String> errors = new HashMap<String, String>();
		if (!StringUtils.isEmpty(parameterRuleValue))
		{
			this.validateParameters(errors, parameterRuleValue, request, handlerMethod);
		}
		this.validateFiles(errors, fileRuleValue, request, handlerMethod);

		if (errors.size() > 0)
		{
			request.setAttribute("errors", errors);
			String errorPage = this.getValueFromResources(handlerMethod, handlerName + ".error");
			if (StringUtils.isEmpty(errorPage))
			{
				errorPage = this.getValueFromResources(handlerMethod, "error");
			}
			request.getRequestDispatcher(errorPage).forward(request, response);
			return false;
		}
		return true;
	}

	private String getValueFromResources(HandlerMethod handlerMethod, String key)
	{
		Object handlerBean = handlerMethod.getBean();
		String validationRule = null;
		try
		{
			Method getValueMethod = handlerBean.getClass().getMethod("getValue", String.class);
			validationRule = (String) getValueMethod.invoke(handlerBean, key);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}

		return validationRule;
	} 

	private void validateFiles(Map<String, String> errors, String rules, HttpServletRequest request,
			HandlerMethod handlerMethod)
	{
		MultipartResolver multipartResolver = new CommonsMultipartResolver();
		if (multipartResolver.isMultipart(request))
		{
			if (StringUtils.isEmpty(rules))
			{
				throw new RuntimeException("文件类型验证规则不能为空!");
			}
			String[] allRulesInStringArray = rules.split("\\|");
			List<String> allRulesInStringList = Arrays.asList(allRulesInStringArray);
			MultipartRequest multipartRequest = (MultipartRequest) request;
			Map<String, MultipartFile> allUploadedFiles = multipartRequest.getFileMap();
			if(allUploadedFiles.size() > 0)
			{
				Set<Map.Entry<String, MultipartFile>> entrySet = allUploadedFiles.entrySet();
				for(Map.Entry<String, MultipartFile> eachEntry : entrySet)
				{
					String contentType = eachEntry.getValue().getContentType();
					if(!allRulesInStringList.contains(contentType))
					{
						errors.put(eachEntry.getValue().getOriginalFilename(), this.getValueFromResources(handlerMethod, "validation.mime.msg"));
					}
				}
			}
		} 
	}

	private void validateParameters(Map<String, String> errors, String rules, HttpServletRequest request,
			HandlerMethod handlerMethod)
	{
		if (StringUtils.isEmpty(rules))
		{
			throw new RuntimeException("数据验证规则不能为空!");
		}

		String[] allRulesInStringArray = rules.split("\\|");
		for (String eachRule : allRulesInStringArray)
		{
			String[] eachRuleInStringArray = eachRule.split(":");
			if (eachRuleInStringArray.length != 2)
			{
				throw new RuntimeException("数据验证规则不和法，正确的格式是:(参数名称:参数类型)");
			}

			String expectedParameterName = eachRuleInStringArray[0];
			String expectedParameterType = eachRuleInStringArray[1];

			String parameterValueInString = request.getParameter(expectedParameterName);

			if (StringUtils.isEmpty(parameterValueInString))
			{
				errors.put(expectedParameterName,
						this.getValueFromResources(handlerMethod, "data.should.not.empty"));
				continue;
			}

			if ("int".equalsIgnoreCase(expectedParameterType))
			{
				if (!parameterValueInString.matches("\\d+"))
				{
					errors.put(expectedParameterName,
							this.getValueFromResources(handlerMethod, "int.invalid"));
					continue;
				}
			}

			if ("double".equalsIgnoreCase(expectedParameterType))
			{
				if (!parameterValueInString.matches("\\d+(\\.\\d+)?"))
				{
					errors.put(expectedParameterName,
							this.getValueFromResources(handlerMethod, "double.invalid"));
					continue;
				}
			}

			if ("date".equalsIgnoreCase(expectedParameterType))
			{
				if (!parameterValueInString.matches("\\d+{4}-\\d{2}-\\d{2}"))
				{
					errors.put(expectedParameterName,
							this.getValueFromResources(handlerMethod, "date.invalid"));
					continue;
				}
			}

		}
	}

}
