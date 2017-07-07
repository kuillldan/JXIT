package bitool.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.xml.IfTag;

import bitool.enums.OpenOffStatus;
import bitool.enums.UserType;
import bitool.factory.ServiceFactory;
import bitool.service.impl.OpenOffManagementServiceImpl;
import bitool.utils.CONST;
import bitool.vo.AccountManagement;
import bitool.vo.OpenOffManagement;

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

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String ipAddress = request.getRemoteAddr();
			System.out.println(ipAddress);
			AccountManagement accountManagement = ServiceFactory
					.getAccountManagementServiceInstance()
					.findAccountByIpAddress(ipAddress);

			if (null == accountManagement)
			{
				this.prevent(request, response, "user not found",
						CONST.errorPageJSP);
			} else
			{
				OpenOffManagement openOffManagement = ServiceFactory
						.getOpenOffManagementServiceInstance()
						.findOpenOffManagement();

				System.out.println("UserType:"
						+ accountManagement.getUserType());
				System.out.println("Stauts:" + openOffManagement.getStatus());

				if (OpenOffStatus.CLOSED.toString().equals(
						openOffManagement.getStatus()))
				{
					this.prevent(request, response,
							"current closed for all user", CONST.errorPageJSP);
				} else
				{
					if (OpenOffStatus.ADMIN_OPEN.toString().equals(
							openOffManagement.getStatus()))
					{
						if (UserType.NORMAL.toString().equals(
								accountManagement.getUserType()))
						{
							// Normal user && Admin Open Status
							this.prevent(request, response,
									"current closed for normal user",
									CONST.errorPageJSP);
						} else
						{
							request.getRequestDispatcher(
									"/pages/ProxyServlet/hello.jsp").forward(
									request, response);
						}

					} else
					{
						request.getRequestDispatcher(
								"/pages/ProxyServlet/hello.jsp").forward(
								request, response);
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			request.getRequestDispatcher(CONST.errorPageJSP).forward(request,
					response);
		}
	}

	private void prevent(HttpServletRequest request,
			HttpServletResponse response, String msg, String url)
			throws Exception
	{
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.getRequestDispatcher(CONST.forwardPageJSP).forward(request,
				response);
	}
}
