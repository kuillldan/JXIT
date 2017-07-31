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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bitool.factory.ServiceFactory;
import bitool.utils.CONST;
import bitool.utils.General;
import bitool.utils.StringUtils;
import bitool.vo.AccountManagement;

/**
 * 根据用户IP地址从数据库中检索信息，如果找到用户信息，则通过验证
 * 反之则不通过验证
 */
//@WebFilter("/*")
public class A1AccountFilter implements Filter
{
	private static final Logger logger = Logger.getLogger(A1AccountFilter.class);
	private static final String USER_NOT_FOUND = "proxy.user.not.found";
	//private static final String IP_ADDRESS_STORED_IN_SESSION = "ipAddress.stored.in.session";
	private static final String IP_ADDRESS_STORED_IN_REQUEST = "ipAddress.stored.in.request";

	/**
	 * Default constructor.
	 */
	public A1AccountFilter()
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String ipAddressOfUser = httpRequest.getRemoteAddr();

		try
		{
			AccountManagement accountManagement = ServiceFactory.getAccountManagementServiceInstance().findAccountByIpAddress(
					ipAddressOfUser);

			if (accountManagement == null)
			{
				logger.info(CONST.MESSAGE_SOURCE.getString(USER_NOT_FOUND, ipAddressOfUser));
				General.prevent((HttpServletRequest) request, (HttpServletResponse) response,
						CONST.MESSAGE_SOURCE.getString(USER_NOT_FOUND, ipAddressOfUser), CONST.errorPageJSP, logger);
			} else
			{
				request.setAttribute(CONST.IPADDRESS, ipAddressOfUser);
				request.setAttribute(CONST.USERTYPE, accountManagement.getUserType());
				logger.info(CONST.MESSAGE_SOURCE.getString(IP_ADDRESS_STORED_IN_REQUEST, ipAddressOfUser));
				chain.doFilter(request, response);
			}
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			httpRequest.getRequestDispatcher(CONST.errorPageJSP).forward(request, response);
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
