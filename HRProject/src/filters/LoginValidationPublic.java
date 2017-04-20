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
 * Servlet Filter implementation class LoginValidationPublic
 */
@WebFilter(
{ "/pages/public/change_password.jsp", "/login/front/admin/AdminLoginServletFront/logout",
		"/login/front/admin/AdminLoginServletFront/changePassword" })
public class LoginValidationPublic implements Filter
{

	/**
	 * Default constructor.
	 */
	public LoginValidationPublic()
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
		HttpSession session = ((HttpServletRequest) request).getSession();
		String baid = (String) session.getAttribute("baid");
		String faid = (String) session.getAttribute("faid");
		if (StringUtils.isEmpty(baid) && StringUtils.isEmpty(faid))
		{
			System.out.println("[debug]:*******用户未登录" );
			String path = General.setMsgAndUrlInRequest((HttpServletRequest) request, "请先登录", AdminPages.loginPage);
			request.getRequestDispatcher(path).forward(request, response);
		} else
		{
			System.out.println("[debug]:*******已经登录,baid:" + baid + ",faid:" + faid);
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
