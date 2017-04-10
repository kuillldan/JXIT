package org.lyk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lyk.factory.ServiceFactory;
import org.lyk.utils.MD5Code;
import org.lyk.utils.StringUtils;
import org.lyk.vo.Admin;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/pages/back/admin/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet()
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
		String url = "";
		String msg = "";

		HttpSession session = request.getSession();
		String rand = (String) session.getAttribute("rand");
		String code = request.getParameter("code");
		String aid = request.getParameter("aid");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(rand) || StringUtils.isEmpty(code))
		{
			url = "/pages/back/login.jsp";
			msg = "验证码错误";
		} else
		{
			if (!rand.equalsIgnoreCase(code))
			{
				url = "/pages/back/login.jsp";
				msg = "验证码错误";
			} else
			{
				if (StringUtils.isEmpty(aid) || StringUtils.isEmpty(password))
				{
					url = "/pages/back/login.jsp";
					msg = "请提供用户名或密码";
				} else
				{
					Admin admin = new Admin();
					admin.setAid(aid);
					admin.setPassword(new MD5Code().getMD5ofStr(password));
					try
					{
						if (ServiceFactory.getIAdminServiceInstance().login(admin))
						{
							session.setAttribute("aid", admin.getAid());
							url ="/pages/back/index.jsp";
							msg = "登录成功";
						} else
						{
							url = "/pages/back/login.jsp";
							msg = "登录失败-用户名或密码错误";
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		request.getRequestDispatcher("/pages/common/forward.jsp").forward(request, response);
	}

}
