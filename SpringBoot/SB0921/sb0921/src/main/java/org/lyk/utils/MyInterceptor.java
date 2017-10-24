package org.lyk.utils;

import java.lang.invoke.MethodHandle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor
{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception
	{
		HandlerMethod handlerMethod = (HandlerMethod)arg2;
		
		
		System.out.println("********* preHandle");
		System.out.println("调用的函数名:" + handlerMethod.getMethod().getName());
		System.out.println("函数所包含的类名:" + handlerMethod.getBean().getClass().getSimpleName());
		return true;
	}

}
