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

import bitool.factory.ServiceFactory;
import bitool.utils.CONST;
import bitool.vo.AccountManagement;

/**
 * Servlet Filter implementation class AccountFilter
 */
//@WebFilter("/pages/front/*")
public class AccountFilter implements Filter
{

	/**
	 * Default constructor.
	 */
	public AccountFilter()
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
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String ipaddress = httpRequest.getRemoteAddr();
		try
		{
			AccountManagement accountManagement = ServiceFactory.getAccountManagementServiceInstance().findAccountByIpAddress(ipaddress);
			System.out.println(accountManagement);
			if(accountManagement == null)
			{
				httpRequest.setAttribute("msg", "invalid ip address");
				httpRequest.setAttribute("url", CONST.errorPageJSP);
				httpRequest.getRequestDispatcher(CONST.forwardPageJSP).forward(request, response);
			}
			else
			{
				chain.doFilter(request, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
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
