package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pages.AdminPages;
import utils.General;
import utils.StringUtils;

/**
 * Servlet Filter implementation class LoginValidationBack
 */
@WebFilter(
{ "/pages/back/*","/login/back/admin/AdminLoginServletBack/changePassword"})
//,"/login/back/admin/AdminLoginServletBack/changePassword" 
public class LoginValidationBack implements Filter
{

	/**
	 * Default constructor.
	 */
	public LoginValidationBack()
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
		HttpSession session = httpRequest.getSession();
		String aid = (String) session.getAttribute("baid");
		if (StringUtils.isEmpty(aid))
		{
			// 未登录
			httpRequest.getRequestDispatcher(General.setMsgAndUrlInRequest(httpRequest, "请先登录", AdminPages.loginBackPage))
					.forward(request, response);

		} else
		{
			// 已登录
			chain.doFilter(request, response);
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
