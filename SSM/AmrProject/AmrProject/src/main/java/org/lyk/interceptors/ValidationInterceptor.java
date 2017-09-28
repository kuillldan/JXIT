package org.lyk.interceptors;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lyk.utils.CommonConstant;
import org.lyk.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ValidationInterceptor implements HandlerInterceptor
{
	private static final Logger logger = LoggerFactory.getLogger(CommonConstant.LOGFILE);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		logger.debug("*****拦截器" + this.getClass().getSimpleName() + "执行");

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String actionName = this.getActionName(handlerMethod);
		logger.debug("请求的Action名称:" + actionName);
		String validationRuleString = this.getValidationRule(actionName,handlerMethod);
		
		if(StringUtils.isEmpty(validationRuleString))
		{
			logger.debug("验证规则为空,无需进行验证.");
			return true;
		}
		
		logger.debug("验证规则:" + validationRuleString);
		boolean validationPassed = false;
		validationPassed = this.doValidation(request, validationRuleString);
		
		return validationPassed;
	}
	
	private String getValidationRule(String actionName,HandlerMethod handlerMethod)
	{
		Object action = handlerMethod.getBean();
		try
		{
			Method method = action.getClass().getMethod("getValidation",String.class, Object[].class);
			method.setAccessible(true);
			return (String)method.invoke(action, actionName);
		} catch (NoSuchMethodException e)
		{
		} catch (SecurityException e)
		{
		} catch (IllegalAccessException e)
		{
		} catch (IllegalArgumentException e)
		{
		} catch (InvocationTargetException e)
		{
		}
		logger.warn("获取验证规则时发生异常.");
		return null;
	}
	
	
	private String getActionName(HandlerMethod handlerMethod )
	{
		String validationRuleString = null;
		Object action = handlerMethod.getBean();
		String actionName = action.getClass().getSimpleName();
		String methodName = handlerMethod.getMethod().getName(); 
		
		validationRuleString = actionName + "." + methodName;
		return validationRuleString;
	}
	
	private boolean doValidation(HttpServletRequest request,String validationRuleString)
	{
		boolean validationPassed = true;
		String[] allRules = validationRuleString.split("\\|");
		for(String eachRule : allRules)
		{
			String requiredField = eachRule.split("\\:")[0];
			String requiredType = eachRule.split("\\:")[1];
			
			String acturalValue = request.getParameter(requiredField);
			if(StringUtils.isEmpty(acturalValue))
			{
				logger.debug("未能获取到指定字段("+requiredField+")的值.");
				validationPassed = false;
				continue;
			}
			
			if("String".equalsIgnoreCase(requiredType))
			{
				continue;
			}
			
			if("Integer".equalsIgnoreCase(requiredType) || "int".equalsIgnoreCase(requiredType)) 
			{
				if(!this.validateInteger(acturalValue))
				{
					validationPassed = false;
				}
			}
			
		}
		
		return validationPassed;
	}

	private boolean validateInteger(String data)
	{
		if (data.matches("\\d+"))
			return true;

		return false;
	}

	private boolean validateDouble(String data)
	{
		if (data.matches("\\d+(\\.\\d+)"))
			return true;
		return false;
	}

	private boolean validateFloat(String data)
	{
		return this.validateDouble(data);
	}

	private boolean validateDate(String data)
	{
		if(data.matches("\\d{4}-\\d{2}-\\d{2}"))
			return true;
		
		if(data.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))
			return true;
		
		if(data.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
			return true;
		
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception
	{

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		// TODO Auto-generated method stub

	}
}
