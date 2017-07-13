package org.lyk.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
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
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		System.out.println(handlerMethod.getBean().getClass().getSimpleName() + "."
				+ handlerMethod.getMethod().getName());
		String handlerName = handlerMethod.getBean().getClass().getSimpleName() + "."
				+ handlerMethod.getMethod().getName();
		String ruleKey = handlerName + ".rules";
		String validationRule = this.getValueFromResources(handlerMethod, ruleKey);
		if (!StringUtils.isEmpty(validationRule))
		{
			Map<String, String> errors = this.doValidation(validationRule, request, handlerMethod);
			if (errors.size() > 0)
			{
				request.setAttribute("errors", errors);
				String errorPage = this.getValueFromResources(handlerMethod, handlerName + ".error");
				if (StringUtils.isEmpty(errorPage))
				{
					errorPage = this.getValueFromResources(handlerMethod, "error");
				}
				System.out.println("******************" + errors);
				System.out.println("*****************request:" + request);
				System.out.println("*****************request:" + response);
				System.out.println("*****************errorPage:" + errorPage);
				request.getRequestDispatcher(errorPage).forward(request, response);
				return false;
			}
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
		}

		return validationRule;
	}

	private Map<String, String> doValidation(String rules, HttpServletRequest request,
			HandlerMethod handlerMethod)
	{
		Map<String, String> errors = new HashMap<String, String>();

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
		
		return errors;
	}

}
