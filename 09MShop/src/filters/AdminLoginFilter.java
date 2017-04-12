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
 * Servlet Filter implementation class AdminLoginFilter
 */
@WebFilter("/pages/back/admin/*")
public class AdminLoginFilter implements Filter
{

	/**
	 * Default constructor.
	 */
	public AdminLoginFilter()
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
		HttpSession session = ((HttpServletRequest) request).getSession();
		String aid = (String) session.getAttribute("aid");
		if (StringUtils.isEmpty(aid))
		{
			String msg = "请先登录";
			String url = CONST.pageAdminLogin;
			((HttpServletRequest) request).setAttribute("msg", msg);
			((HttpServletRequest) request).setAttribute("url", url);

			request.getRequestDispatcher(CONST.pageForward).forward(request, response);
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
