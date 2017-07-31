package bitool.filter;

import java.io.IOException;

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

import bitool.enums.OpenOffStatus;
import bitool.enums.UserType;
import bitool.factory.ServiceFactory;
import bitool.utils.CONST;
import bitool.utils.General;
import bitool.utils.StringUtils;
import bitool.vo.AccountManagement;
import bitool.vo.OpenOffManagement;

/**
 * Servlet Filter implementation class OpenOffManagementFilter
 */
//@WebFilter("/svc_state_manage/*")
public class OpenOffManagementFilter implements Filter
{
	private static final String ONLY_ADMIN_ALLOWED = "openOffManagement.only.admin.allowed";
	private static final String USER_NOT_FOUNT = "proxy.user.not.found";
	// private static final String VALIDATION_PASSED =
	// "openOffManagement.validation.passed";
	// private static final String OPEN_OFF_MANAGEMENT = "OpenOffManagement";
	private static final Logger logger = Logger.getLogger(OpenOffManagement.class);

	/**
	 * Default constructor.
	 */
	public OpenOffManagementFilter()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stu
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException
	{
		// TODO Auto-generated method stub
		// place your code here

		// 验证用户是否存在，以及根据用户类型（NORMAL/ADMIN）决定用户是否具有权限访问开闭局管理页面
		// pass the request along the filter chain
		try
		{
			String userType = (String) request.getAttribute(CONST.USERTYPE);
			if (UserType.ADMIN.toString().equals(userType))
			{
				chain.doFilter(request, response);
			} else
			{
				General.prevent((HttpServletRequest) request, (HttpServletResponse) response,
						CONST.MESSAGE_SOURCE.getString(ONLY_ADMIN_ALLOWED), CONST.errorPageJSP, logger);
			}

		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			request.getRequestDispatcher(CONST.errorPageJSP).forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}
}
