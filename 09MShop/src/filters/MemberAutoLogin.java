package filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import factories.ServiceFrontFactory;
import utils.CookieUtils;
import utils.StringUtils;
import vo.Member;

/**
 * Servlet Filter implementation class AutoLogin
 */
@WebFilter(urlPatterns =
{ "/index.jsp", "/pages/front/*" })
public class MemberAutoLogin implements Filter
{

	/**
	 * Default constructor.
	 */
	public MemberAutoLogin()
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
		Map<String, String> cookies = CookieUtils.load((HttpServletRequest)request);
		String mid = cookies.get("mid");
		String password = cookies.get("password");
		if(!StringUtils.isEmpty(mid) && !StringUtils.isEmpty(password))
		{
			Member vo = new Member();
			vo.setMid(mid);
			vo.setPassword(password);
			try
			{
				if(ServiceFrontFactory.getMemberServiceFrontInstance().login(vo))
				{
					HttpSession session = ((HttpServletRequest)request).getSession();
					session.setAttribute("mid", vo.getMid());
					session.setAttribute("photo", vo.getPhoto());
				}
			} catch (Exception e)
			{ 
				e.printStackTrace();
			}
		}
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
