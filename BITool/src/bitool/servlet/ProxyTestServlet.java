package bitool.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProxyTestServlet
 */
//@WebServlet(value =
//{ "/pages/ProxyServlet/*" }, initParams =
//{ //@WebInitParam(name = "targetUri", value = "http://localhost:8080/AA/index.jsp"),
//		@WebInitParam(name = "log", value = "true"),
//		@WebInitParam(name = "redirectURL", value = "http://10.43.142.134:8080") 
//		//@WebInitParam(name = "redirectURL", value = "http://139.199.220.102") 
//		})
public class ProxyTestServlet extends ProxyServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProxyTestServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		System.out.println(this.getClass().getSimpleName());
	}

}
