package filters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@WebFilter(urlPatterns =
{ "/pages/front/shopCart/*","/pages/front/member/*" })
public class MemberLoginVlidation implements Filter
{

	/**
	 * Default constructor.
	 */
	public MemberLoginVlidation()
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
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String mid = (String) session.getAttribute("mid");
		if (StringUtils.isEmpty(mid))
		{
			// 未登录
			//System.out.println( "Filter 登录检查"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
			String msg = "你还没有登录，请先登录!";
			String url = CONST.pageMemberLogin;

			httpRequest.setAttribute("msg", msg);
			httpRequest.setAttribute("url", url);
			httpRequest.getRequestDispatcher(CONST.pageForward).forward(httpRequest, response);
		} 
		else
		{
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
