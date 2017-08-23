package org.lyk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DataValidator implements HandlerInterceptor
{
	private static final Logger logger = LoggerFactory.getLogger(DataValidator.class);
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception
	{
		logger.info("**************afterCompetion");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception
	{
		logger.info("**************postHandle");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception
	{
		logger.info("**************preHandle");
		request.setAttribute("errorMsg", "内部错误");
		request.getRequestDispatcher("/pages/common/error.jsp").forward(request, response);
		return false;
	}

}
