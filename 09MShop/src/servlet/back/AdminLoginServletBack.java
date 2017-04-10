package servlet.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factories.ServiceBackFactory;
import utils.CONST;
import utils.General;
import utils.MD5Code;
import utils.StringUtils;
import vo.Admin;

/**
 * Servlet implementation class AdminLoginServletBack
 */
@WebServlet("/pages/back/AdminLoginServletBack/*")
public class AdminLoginServletBack extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLoginServletBack()
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
		String status = General.getStatus(request);
		String path = CONST.pageError;
		if ("login".equals(status))
		{
			path = this.login(request);
		}else if("logout".equals(status))
		{
			path = this.logout(request);
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	private String logout(HttpServletRequest request)
	{
		String msg = null;
		String url = null;
		
		HttpSession session = request.getSession();
		session.invalidate();
		msg = "注销成功";
		url = CONST.pageAdminLogin;
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return CONST.pageForward;
	}

	private String login(HttpServletRequest request)
	{
		String msg = null;
		String url = null;
		HttpSession session = request.getSession();

		String aid = request.getParameter("aid");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		String rand = (String) session.getAttribute("rand");

		try
		{
			if (StringUtils.isEmpty(code) || StringUtils.isEmpty(rand))
			{
				msg = "验证码错误";
				url = CONST.pageAdminLogin;
			} else
			{
				if (!StringUtils.isTheSame(code, rand))
				{
					msg = "验证码错误";
					url = CONST.pageAdminLogin;
				} else
				{
					Admin vo = new Admin();
					vo.setAid(aid);
					vo.setPassword(new MD5Code().getMD5ofStr(password));
					if(ServiceBackFactory.getIAdminServiceBackInstance().login(vo))
					{
						msg = "登录成功";
						url = CONST.pageAdminIndex;
						
						session.setAttribute("aid", vo.getAid());
						session.setAttribute("lastdate", vo.getLastdate());
					}
					else
					{
						msg = "登录失败，请检查用户名或密码";
						url = CONST.pageAdminLogin;
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return CONST.pageForward;
	}

}
