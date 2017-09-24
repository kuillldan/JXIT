package org.lyk.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport
{
	public String login()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if("root".equals(userName) && "admin".equals(password))
		{
			System.out.println("*********成功");
			request.getSession().setAttribute("mid", userName);
			return "welcome.page";
		}
		else
		{
			System.out.println("*********失败");
			request.setAttribute("msg", "用户名或密码错误");
			request.setAttribute("url", "login.jsp");
			return "forward.page";
		} 
	}
}
