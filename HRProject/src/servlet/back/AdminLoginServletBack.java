package servlet.back;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import messages.AdminMessages;
import pages.AdminPages;
import factories.ServiceBackFactory;  
import utils.AbstractServlet;
import utils.CONST;
import utils.MD5Code;
import utils.StringUtils;
import vo.Admin;


@SuppressWarnings("serial")
@WebServlet("/login/back/admin/AdminLoginServletBack/*")
public class AdminLoginServletBack extends AbstractServlet
{
	private String loginValidation = "admin.aid|admin.password";
	private Admin admin = new Admin();
	public Admin getAdmin()
	{
		return admin;
	}
	
	
	
	protected String login()
	{
		try
		{
			if (!super.checkCode())
			{
				// 返回登录页面 验证码错误
				return super.setMsgAndUrlInRequest(AdminMessages.codeError, AdminPages.loginBackPage);
			} else
			{// 验证码正确  
				this.admin.setPassword(new MD5Code().getMD5ofStr(this.admin.getPassword()));
				Map<String, Object> map = ServiceBackFactory.getIAdminServiceBackInstance().login(admin);
				boolean flag = (boolean) map.get("flag");
				if (flag)
				{ 
					// 登录成功
					this.admin = (Admin) map.get("admin"); 
					
					HttpSession session = this.request.getSession(); 
					
					// 后台人事管理员
					super.request.getSession().setAttribute("baid", this.admin.getAid());
					session.setAttribute("bAdmin", this.admin);
					return super.setMsgAndUrlInRequest(AdminMessages.backAdminSuccessfullyLogin, AdminPages.backAdminIndexJSP);
				} else
				{
					return super.setMsgAndUrlInRequest(AdminMessages.userNameOrPasswordError, AdminPages.loginBackPage);
				}
			}
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
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
			
			String url = AdminPages.backChangePasswordJSP;
			if(ServiceBackFactory.getIAdminServiceBackInstance().updatePassword(aid, new MD5Code().getMD5ofStr(oldPassword), new MD5Code().getMD5ofStr(newPassword)))
			{
				String msg = AdminMessages.backAdminSuccessfullChangedPassword;
				return super.setMsgAndUrlInRequest(msg, url);				
			}
			else
			{
				String msg = AdminMessages.backAdminFailedToChangedPassword;
				return super.setMsgAndUrlInRequest(msg, url);
			}
		}
		catch(Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	@Override
	protected String getUploadFolder()
	{
		return null;
	}

	@Override
	protected String getColumns()
	{
		return null;
	}

	@Override
	protected String getColumn()
	{
		return null;
	}

	@Override
	protected String getTitle()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
