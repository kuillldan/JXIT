package org.lyk.filter;

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

import org.lyk.constant.CommonConstant;
import org.lyk.utils.StringUtils;
import org.lyk.vo.Emp;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/pages/*")
public class LoginFilter implements Filter
{

	public LoginFilter()
	{
		// TODO Auto-generated constructor stub
	}

	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest _request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) _request;
		HttpSession session = request.getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		if (emp == null || StringUtils.isEmpty(emp.getName()))
		{
			request.setAttribute("msg", "你还没有登录，请先登录!");
			request.setAttribute("url", "/login.jsp");
			request.getRequestDispatcher("/forward.jsp").forward(request, response);
		} else
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
