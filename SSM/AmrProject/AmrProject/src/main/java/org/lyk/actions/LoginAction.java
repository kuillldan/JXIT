package org.lyk.actions;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lyk.constant.CommonConstant;
import org.lyk.constant.MessageConstant;
import org.lyk.constant.PageConstant;
import org.lyk.service.IEmpService;
import org.lyk.utils.MD5Code;
import org.lyk.vo.Action;
import org.lyk.vo.Dept;
import org.lyk.vo.Emp;
import org.lyk.vo.Groups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/*")
public class LoginAction extends AbstractAction
{
	private static final Logger logger = LoggerFactory.getLogger(CommonConstant.LOGFILE);

	public LoginAction()
	{
	}

	@Resource
	private IEmpService empServiceImpl;

	@RequestMapping("login")
	public ModelAndView login(Emp emp, HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			String msg = null;
			logger.debug("emp:" + emp);

			if (emp.getEid() == null || emp.getPassword() == null)
			{
				msg = "登陆失败,请提供用户名或密码.";
				logger.info(msg);
				super.setForwardMessageAndUrl(mav, msg, super.getPage("login.jsp"));
				return mav;
			}

			emp.setPassword(new MD5Code().getMD5ofStr(emp.getPassword()));
			if (this.empServiceImpl.login(emp))
			{
				msg = "登陆成功";
				// super.setForwardMessageAndUrl(mav, msg,
				// super.getPage("index.jsp"));
				mav.setViewName(super.getPage("index.jsp"));
				HttpSession session = request.getSession();
				session.setAttribute("emp", emp);
				logger.info(msg);
			} else
			{
				msg = "登陆失败";
				super.setForwardMessageAndUrl(mav, msg, super.getPage(PageConstant.LOGIN_JSP));
				logger.info(msg);
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "用户登陆", e);
		}
		return mav;
	}

	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			HttpSession session = request.getSession();
			session.invalidate();
			String msg = super.getMessage("logout.success");
			super.setForwardMessageAndUrl(mav, msg, super.getPage("login.jsp"));
			logger.info(msg);
		} catch (Exception e)
		{
			super.setSystemError(mav, "用户注销", e);
		}
		return mav;
	}
}
