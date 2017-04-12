package servlet.front;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factories.ServiceFrontFactory;
import utils.CONST;
import utils.CookieUtils;
import utils.General;
import utils.MD5Code;
import utils.StringUtils;
import vo.Member;

/**
 * Servlet implementation class MemberServletFront
 */
@WebServlet("/pages/MemberServletFront/*")
public class MemberServletFront extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberServletFront()
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
		if("regist".equals(status))
		{
			path = this.regist(request);
		}
		else if("active".equals(status))
		{
			path = this.active(request);
		}else if("login".equals(status))
		{
			path = this.login(request,response);
		}else if("logout".equals(status))
		{ 
			path = this.logout(request, response);
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	} 
	
	private String logout(HttpServletRequest request, HttpServletResponse response)
	{ 
		CookieUtils.clear(request, response);
		HttpSession session = request.getSession();
		session.removeAttribute("mid");
		session.removeAttribute("photo");
		session.invalidate();
		String msg = "退出成功";
		String url = CONST.pageMemberIndex;
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return CONST.pageForward;
	}
	
	private String login(HttpServletRequest request,HttpServletResponse response)
	{
		String msg = null;
		String url = null;
		HttpSession session = request.getSession();
		
		String mid = request.getParameter("mid");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		
		String rand = (String)session.getAttribute("rand");
		
		try
		{
			if(StringUtils.isEmpty(rand) || StringUtils.isEmpty(code))
			{
				msg = "验证码错误";
				url = CONST.pageMemberLogin;
			}
			else
			{
				if(!StringUtils.isTheSame(code, rand))
				{
					msg = "验证码错误";
					url = CONST.pageMemberLogin;
				}
				else
				{
					Member vo = new Member();
					vo.setMid(mid);
					vo.setPassword(new MD5Code().getMD5ofStr(password));
					if(ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo))
					{
						//System.out.println("[debug] 前台登录成功. mid = " + vo.getMid() + ",photo = " + vo.getPhoto());
						msg = "登录成功!";
						url = CONST.pageMemberIndex;
						session.setAttribute("mid", mid);
						session.setAttribute("photo",vo.getPhoto());
						
						if(!StringUtils.isEmpty(request.getParameter("autoLogin")))
						{
							Integer expiry = 60 * 60 * 24 * 7;//默认7天内免登陆
							try
							{
								expiry = Integer.parseInt(request.getParameter("autoLogin"));
							}catch(Exception e){}
							
							CookieUtils.save(response, expiry, "mid", vo.getMid());
							CookieUtils.save(response, expiry, "password", vo.getPassword());
						}
					}
					else
					{
						msg = "登录失败，请检查用户名或密码!";
						url = CONST.pageMemberLogin;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return CONST.pageForward;
	}
	
	/**
	 * 注册功能
	 * @param request
	 * @return
	 */
	private String regist(HttpServletRequest request)
	{
		String msg = null;
		String url = null;
		
		String mid = request.getParameter("mid");
		String password = request.getParameter("password");
		
		if(!StringUtils.isEmpty(mid) && !StringUtils.isEmpty(password))
		{
			Member vo = new Member();
			vo.setMid(mid);
			vo.setPassword(new MD5Code().getMD5ofStr(password));
			vo.setCode(UUID.randomUUID().toString());
			vo.setPhoto(CONST.noPhoto);
			vo.setRegdate(new Date());
			vo.setStatus(CONST.MemberStatus.PENDING.ordinal());
			
			try
			{
				if(ServiceFrontFactory.getIMemberServiceFrontInstance().regist(vo))
				{
					msg ="用户注册成功,请进行账户激活";
					url = CONST.pageMemberIndex;
					System.out.println("【发激活邮件】" + General.getBasePath(request) + "pages/MemberServletFront/active?mid=" + mid + "&code=" + vo.getCode());
				}
				else
				{
					msg = "用户注册失败，该用户名可能已存在。";
					url = CONST.pageRegist;
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return CONST.pageForward;
	}
	
	
	public String active(HttpServletRequest request)
	{
		String msg = null;
		String url = null;
		
		String mid = request.getParameter("mid");
		String code = request.getParameter("code");
		
		try
		{
			if(ServiceFrontFactory.getIMemberServiceFrontInstance().active(mid, code))
			{
				msg = "用户激活成功";
				url = CONST.pageMemberIndex;
			}
			else
			{
				msg = "用户激活失败";
				url = CONST.pageError;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return CONST.pageForward;
	}

}
