package bitool.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitool.factory.ServiceFactory;
import bitool.utils.CONST;
import bitool.vo.AccountManagement;

/**
 * Servlet implementation class Determin
 */
@SuppressWarnings("serial")
@WebServlet("/pages/bifunction/BIFunctionServlet/*")
public class BIFunctionServlet extends HttpServlet
{

	public BIFunctionServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		try
		{
			String ipAddress = request.getRemoteAddr();
			AccountManagement accountManagement = ServiceFactory.getAccountManagementServiceInstance()
					.findAccountByIpAddress(ipAddress);
			
			if(null == accountManagement)
			{
				request.setAttribute("msg", "user not found");
				request.setAttribute("url", CONST.errorPageJSP);
				request.getRequestDispatcher(CONST.forwardPageJSP).forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("/pages/bifunction/show.jsp").forward(request, response);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
			request.getRequestDispatcher(CONST.errorPageJSP).forward(request, response);
		}
	}

}
