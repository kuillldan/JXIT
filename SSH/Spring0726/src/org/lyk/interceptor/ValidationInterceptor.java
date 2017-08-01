package org.lyk.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ValidationInterceptor implements HandlerInterceptor
{
	private static Logger logger = Logger.getLogger(ValidationInterceptor.class);

	private static final String RULES = "rules";
	private static final String GETMESSAGE = "getMessage";

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handlerMethod, Exception e) throws Exception
	{
		logger.info("afterCompletion:" + handlerMethod.getClass().getName());
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handlerMethod,
			ModelAndView modelAndView) throws Exception
	{
		// TODO Auto-generated method stub
		logger.info("postHandle:" + handlerMethod.getClass().getName());
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object _handlerMethod)
			throws Exception
	{
		// TODO Auto-generated method stub
		HandlerMethod handlerMethod = (HandlerMethod) _handlerMethod;
		logger.info("********preHandle********");
		Object actionBean = handlerMethod.getBean();
		String actionName = actionBean.getClass().getSimpleName();
		Method actionMethod = handlerMethod.getMethod();
		String methodName = actionMethod.getName();
		String validationRuleKey = actionName + "." + methodName + "." + RULES;
		Method getMessageMethod = actionBean.getClass().getMethod(GETMESSAGE, String.class, Object[].class);
		String validationRuleValue = (String)getMessageMethod.invoke(actionBean, validationRuleKey,null);
		System.out.println(validationRuleValue);
		logger.info("********preHandle********");
		return true;
	}

}
