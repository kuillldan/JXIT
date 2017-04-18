package servlet.front;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pages.AdminPages;
import enums.AdminType;
import factories.ServiceFrontFactory;
import messages.AdminMessage;
import utils.AbstractServlet;
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

	protected String login()
	{
		try
		{
			if (!super.checkCode())
			{
				// 返回登录页面 验证码错误
				return super.setMsgAndUrlInRequest(AdminMessage.codeError, AdminPages.loginPage);
			} else
			{// 验证码正确
				this.admin.setPassword(new MD5Code().getMD5ofStr(this.admin.getPassword()));
				Map<String, Object> map = ServiceFrontFactory.getIAdminServiceFrontInstance().login(admin);
				boolean flag = (boolean) map.get("flag");  
				if (flag)
				{
					// 登录成功
					this.admin = (Admin) map.get("admin");
					if (this.admin.getType().equals(AdminType.FRONT_ADMIN.ordinal()))
					{
						// 前台人事管理员
						super.request.getSession().setAttribute("aid", this.admin.getAid());
						return super.setMsgAndUrlInRequest(AdminMessage.frontAdminSuccessfullyLogin,
								AdminPages.frontAdminIndexJSP);
					} else if (this.admin.getType().equals(AdminType.BACK_ADMIN.ordinal()))
					{
						// 后台人事管理员
						super.request.getSession().setAttribute("aid", this.admin.getAid());
						return super.setMsgAndUrlInRequest(AdminMessage.backAdminSuccessfullyLogin,
								AdminPages.backAdminIndexJSP);
					} else
					{
						throw new Exception(AdminMessage.illegalAdminType);
					}
				} else
				{
					return super.setMsgAndUrlInRequest(AdminMessage.userNameOrPasswordError, AdminPages.loginPage);
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
			return super.setMsgAndUrlInRequest("退出成功", AdminPages.loginPage);
			//this.request.getRequestDispatcher(AdminPages.loginPage).forward(request, response);
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

}
