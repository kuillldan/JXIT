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
 

import utils.CONST;
import utils.StringUtils;

/**
 * Servlet Filter implementation class LoginVlidation
 */
@WebFilter("/*")
public class LoginVlidation implements Filter
{

	/**
	 * Default constructor.
	 */
	public LoginVlidation()
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		// HttpServletRequest httpRequest = (HttpServletRequest)request;
		// HttpSession session = httpRequest.getSession();
		// String uid = (String)session.getAttribute("uid");
		// if(StringUtils.isEmpty(uid))
		// {
		// //未登录
		// String msg = "你还没有登录，请先登录!";
		// String url = CONST.loginPage;
		//
		// httpRequest.setAttribute("msg", msg);
		// httpRequest.setAttribute("url", url);
		// httpRequest.getRequestDispatcher(CONST.forwardPage).forward(httpRequest,
		// response);
		// }
		// else
		// {
		// chain.doFilter(request, response);
		// }
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}

}
