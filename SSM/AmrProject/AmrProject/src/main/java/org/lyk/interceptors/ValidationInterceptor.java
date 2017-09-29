package org.lyk.interceptors;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	public ValidationInterceptor()
	{
		logger.debug("*****拦截器" + this.getClass().getSimpleName() + "创建");
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		logger.debug("*****拦截器" + this.getClass().getSimpleName() + "执行");

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String actionName = this.getActionName(handlerMethod);
		logger.debug("请求的Action名称:" + actionName);
		String validationRuleString = this.getValidationRule(actionName, handlerMethod);

		if (StringUtils.isEmpty(validationRuleString))
		{
			logger.debug("验证规则为空,无需进行验证.");
			return true;
		}

		logger.debug("验证规则:" + validationRuleString);
		Map<String, String> errors = new HashMap<>();
		boolean validationPassed = this.doValidation(request, validationRuleString,errors);
		if(validationPassed)
		{
			logger.debug("数据合法,验证通过");
		}
		else
		{
			String msg = "数据不合法,未能通过数据验证";
			logger.info(msg);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return validationPassed;
	}

	private String getValidationRule(String actionName, HandlerMethod handlerMethod)
	{
		Object action = handlerMethod.getBean();
		try
		{
			Method getValidationMethod = action.getClass().getMethod("getValidation", String.class, Object[].class);
			getValidationMethod.setAccessible(true);
			return (String) getValidationMethod.invoke(action, actionName,null);
		} catch (NoSuchMethodException e)
		{
			//logger.warn(e.getMessage(),e);
		} catch (SecurityException e)
		{
			logger.warn("获取验证规则时发生异常.");
			logger.warn(e.getMessage(),e);
		} catch (IllegalAccessException e)
		{
			//logger.warn(e.getMessage(),e);
		} catch (IllegalArgumentException e)
		{
			logger.warn("获取验证规则时发生异常.");
			logger.warn(e.getMessage(),e);
		} catch (InvocationTargetException e)
		{
//			logger.warn("获取验证规则时发生异常.");
//			logger.warn(e.getMessage(),e);
		}
		//logger.warn("获取验证规则时发生异常.");
		return null;
	}

	private String getActionName(HandlerMethod handlerMethod)
	{
		String validationRuleString = null;
		Object action = handlerMethod.getBean();
		String actionName = action.getClass().getSimpleName();
		String methodName = handlerMethod.getMethod().getName();

		validationRuleString = actionName + "." + methodName;
		return validationRuleString;
	}

	private boolean doValidation(HttpServletRequest request, String validationRuleString,Map<String, String> errors)
	{
		boolean validationPassed = true;
		String[] allRules = validationRuleString.split("\\|");
		for (String eachRule : allRules)
		{
			String requiredField = eachRule.split("\\:")[0];
			String requiredType = eachRule.split("\\:")[1];

			String acturalValue = request.getParameter(requiredField);
			if (StringUtils.isEmpty(acturalValue))
			{
				String msg = "未能获取到指定字段(" + requiredField + ")的值.数据验证失败.";
				errors.put(requiredField, msg);
				logger.info(msg);
				validationPassed = false;
				continue;
			}

			if ("String".equalsIgnoreCase(requiredType))
			{
				continue;
			}

			if ("Integer".equalsIgnoreCase(requiredType) || "int".equalsIgnoreCase(requiredType))
			{
				if (!this.validateInteger(acturalValue))
				{
					String msg = "字段("+requiredField+")必须为整型";
					errors.put(requiredField, msg);
					logger.info(msg);
					validationPassed = false;
				}

				continue;
			}

			if ("Double".equalsIgnoreCase(requiredType))
			{
				if (!this.validateDouble(acturalValue))
				{
					String msg = "字段("+requiredField+")必须为Double型";
					errors.put(requiredField, msg);
					logger.info(msg);
					validationPassed = false;
				}
				continue;
			}

			if ("Float".equalsIgnoreCase(requiredType))
			{
				if (!this.validateFloat(acturalValue))
				{
					String msg = "字段("+requiredField+")必须为Float型";
					errors.put(requiredField, msg);
					logger.info(msg);
					validationPassed = false;
				}

				continue;
			}

			if ("Date".equalsIgnoreCase(requiredType))
			{
				if (!this.validateDate(acturalValue))
				{
					String msg = "字段("+requiredField+")必须为Date型";
					errors.put(requiredField, msg);
					logger.info(msg);
					validationPassed = false;
				}
				continue;
			}
			
			if("Rand".equalsIgnoreCase(requiredType))
			{
				
				HttpSession session = request.getSession();
				String codeInSession = (String)session.getAttribute("rand");
				if(StringUtils.isEmpty(codeInSession))
				{
					String msg = "验证规则要求有验证码，但系统内存中找不到验证码。";
					errors.put(requiredField, msg);
					logger.info(msg);
					validationPassed = false;
					continue;
				}
				
				if(!acturalValue.equalsIgnoreCase(codeInSession))
				{
					String msg = "验证码错误";
					errors.put(requiredField, msg);
					logger.info(msg);
					validationPassed = false;
					continue;
				}
				
				continue;
			}
			
			logger.warn("不支持的数据类型("+requiredType+"),无法完成数据验证。");
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
		if (data.matches("\\d{4}-\\d{2}-\\d{2}"))
			return true;

		if (data.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))
			return true;

		if (data.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
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
