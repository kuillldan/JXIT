package bitool.filter;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import bitool.enums.OpenOffMode;
import bitool.enums.OpenOffStatus;
import bitool.enums.UserType;
import bitool.factory.ServiceFactory;
import bitool.utils.CONST;
import bitool.utils.General;
import bitool.vo.AccountManagement;
import bitool.vo.OpenOffManagement;

/**
 * 验证用户ip地址+当前开闭局状态 如果验证不通过-->跳转到错误页面并给出相关提示
 */
//@WebFilter(value =
//{ "/pages/ProxyServlet/*" })
public class ProxyAutorizationFilter implements Filter
{
	private static final Logger logger = Logger.getLogger(ProxyAutorizationFilter.class);
	
	private static final String CLOSED_FOR_ALL_USER = "openOffManagement.current.is.closed.for.all.user";
	private static final String ONLY_ADMIN_USER_ALLOWED = "openOffManagement.only.admin.allowed";
	private static final String VALIDATION_PASSED = "openOffManagement.validation.passed";
	private static final String CURRENT_TIME_OUTOF_RANGE = "current.time.out.of.range";

	/**
	 * Default constructor.
	 */
	public ProxyAutorizationFilter()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain chain) throws IOException,
			ServletException
	{

		HttpServletRequest request = (HttpServletRequest) _request;
		HttpServletResponse response = (HttpServletResponse) _response;
		String userType = (String) _request.getAttribute(CONST.USERTYPE);
		boolean validationPassed = true;

		try
		{

			OpenOffManagement openOffManagement = ServiceFactory.getOpenOffManagementServiceInstance().findOpenOffManagement();

			// 当前模式(mode)为MANUAL-->禁止所有用户
			if (OpenOffMode.MANUAL.toString().equals(openOffManagement.getMode()))
			{
				validationPassed = false;
				General.prevent(request, response, CONST.MESSAGE_SOURCE.getString(CLOSED_FOR_ALL_USER), CONST.errorPageJSP,
						logger);
			} else if (OpenOffMode.MAINTAINANCE.toString().equals(openOffManagement.getMode()))
			{
				// 当前模式为MAINTAINANCE-->禁止普通用户
				// 只有ADMIN用户可以访问
				if (!UserType.ADMIN.toString().equals(userType))
				{
					validationPassed = false;
					General.prevent(request, response, CONST.MESSAGE_SOURCE.getString(ONLY_ADMIN_USER_ALLOWED),
							CONST.errorPageJSP, logger);
				}
			} else if (OpenOffMode.AUTO.toString().equals(openOffManagement.getMode()))
			{
				// 当前模式为AUTO
				// 需要判断当前时间是否在设定的时间范围内
				// 如果在设定的时间范围内则用户可以访问，否则不能访问

				String startHour = openOffManagement.getStartTime().split(":")[0];
				String startMinute = openOffManagement.getStartTime().split(":")[1];
				String endHour = openOffManagement.getEndTime().split(":")[0];
				String endMinute = openOffManagement.getEndTime().split(":")[1];

				if (!this.isCurrentTimeInGivenRange(startHour, startMinute, endHour, endMinute))
				{
					// 当前时间不在设定的时间范围内
					// 禁止用户访问
					validationPassed = false;
					General.prevent(request, response, CONST.MESSAGE_SOURCE.getString(CURRENT_TIME_OUTOF_RANGE),
							CONST.errorPageJSP, logger);
				}
			}
		} catch (Exception e)
		{
			validationPassed = false;
			logger.error(e.getMessage(), e);
			_request.getRequestDispatcher(CONST.errorPageJSP).forward(_request, _response);
		}

		// 验证用户ip地址+当前开闭局状态
		// 如果验证不通过-->跳转到错误页面并给出相关提示
		if (validationPassed)
		{
			logger.info(_request.getRemoteAddr() + ":" + CONST.MESSAGE_SOURCE.getString(VALIDATION_PASSED));
			chain.doFilter(_request, _response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}

	private boolean isCurrentTimeInGivenRange(String startHour, String startMinute, String endHour, String endMinute)
	{
		Calendar currentTime = Calendar.getInstance();
		Long currentTimeInLong = currentTime.getTimeInMillis();

		Calendar startTime = Calendar.getInstance();
		startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
		startTime.set(Calendar.MINUTE, Integer.parseInt(startMinute));
		startTime.set(Calendar.SECOND, 0);
		startTime.set(Calendar.MILLISECOND, 0);
		Long startTimeInLong = startTime.getTimeInMillis();

		Calendar endTime = Calendar.getInstance();
		endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
		endTime.set(Calendar.MINUTE, Integer.parseInt(endMinute));
		endTime.set(Calendar.SECOND, 0);
		endTime.set(Calendar.MILLISECOND, 0);
		Long endTimeInLong = endTime.getTimeInMillis();

		if (startTimeInLong <= currentTimeInLong && currentTimeInLong <= endTimeInLong)
			return true;
		else
			return false;

	}
}
