package servlet.front;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pages.AdminPages;
import enums.AdminType;
import factories.ServiceFrontFactory;
import messages.AdminMessages;
import utils.AbstractServlet;
import utils.CONST;
import utils.General;
import utils.MD5Code;
import utils.StringUtils;
import vo.Admin;

@SuppressWarnings("serial")
@WebServlet("/login/front/admin/AdminLoginServletFront/*")
public class AdminLoginServletFront extends AbstractServlet
{
	private Admin admin = new Admin();

	public Admin getAdmin()
	{
		return admin;
	}

	private String loginValidation = "admin.aid|admin.password";

	protected String changePassword()
	{
		try
		{
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String aid = request.getParameter("aid"); 
			if(StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword))
			{
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("密码", "旧密码或新密码不能为空");
				request.setAttribute("errors", errors);
				return CONST.errorPage;
			}
			
			String url = AdminPages.frontChangePasswordJSP;
			if(ServiceFrontFactory.getIAdminServiceFrontInstance().updatePassword(aid, new MD5Code().getMD5ofStr(oldPassword), new MD5Code().getMD5ofStr(newPassword)))
			{ 
				String msg = AdminMessages.frontAdminSuccessfullChangedPassword;
				return super.setMsgAndUrlInRequest(msg, url);
				
			}
			else
			{
				String msg = AdminMessages.frontAdminFailedToChangedPassword;
				return super.setMsgAndUrlInRequest(msg, url);
			}
		}
		catch(Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	protected String login()
	{
		try
		{
			if (!super.checkCode())
			{
				// 返回登录页面 验证码错误
				return super.setMsgAndUrlInRequest(AdminMessages.codeError, AdminPages.loginFrontPage);
			} else
			{// 验证码正确 
				this.admin.setPassword(new MD5Code().getMD5ofStr(this.admin.getPassword()));
				Map<String, Object> map = ServiceFrontFactory.getIAdminServiceFrontInstance().login(admin);
				boolean flag = (boolean) map.get("flag");
				if (flag)
				{ 
					// 登录成功
					this.admin = (Admin) map.get("admin"); 
					
					HttpSession session = this.request.getSession(); 
					
					// 前台人事管理员
					super.request.getSession().setAttribute("faid", this.admin.getAid());
					session.setAttribute("fAdmin", this.admin);
					return AdminPages.frontAdminIndexJSP;
					
					
//					if (this.admin.getType().equals(AdminType.FRONT_ADMIN.ordinal()))
//					{
//						
//					} else if (this.admin.getType().equals(AdminType.BACK_ADMIN.ordinal()))
//					{
//						// 后台人事管理员
//						super.request.getSession().setAttribute("baid", this.admin.getAid());
//						session.setAttribute("bAdmin", this.admin);
//						return super.setMsgAndUrlInRequest("后台管理员登录成功", AdminPages.backAdminIndexJSP);
//					} else
//					{
//						throw new Exception(AdminMessage.illegalAdminType);
//					}
				} else
				{
					return super.setMsgAndUrlInRequest(AdminMessages.userNameOrPasswordError, AdminPages.loginFrontPage);
				}

			}
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}

	protected String logout()
	{
		try
		{
			super.request.getSession().invalidate();
			//return super.setMsgAndUrlInRequest("退出成功", AdminPages.loginPage);
			return AdminPages.loginFrontPage;
			// this.request.getRequestDispatcher(AdminPages.loginPage).forward(request,
			// response);
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}

	@Override
	protected String getUploadFolder()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getColumns()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getColumn()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getTitle()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
