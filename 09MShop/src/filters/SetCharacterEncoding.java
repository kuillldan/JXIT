package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class SetCharacterEncoding
 */
@WebFilter("/*")
public class SetCharacterEncoding implements Filter
{
	private String charset = "UTF-8";
	/**
	 * Default constructor.
	 */
	public SetCharacterEncoding()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{ 
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{ 
		//编码过滤
		//登录检查
		request.setCharacterEncoding(this.charset);
		response.setCharacterEncoding(this.charset);
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		if(fConfig.getInitParameter("charset") != null)
		{
			this.charset = fConfig.getInitParameter("charset"); 
		} 
	}

}
