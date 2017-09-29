package org.lyk.actions;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lyk.service.IEmpService;
import org.lyk.utils.CommonConstant;
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

			emp.setPassword(new MD5Code().getMD5ofStr(emp.getPassword()));
			if (this.empServiceImpl.login(emp))
			{
				msg = "登陆成功";
//				super.setForwardMessageAndUrl(mav, msg, super.getPage("index.jsp"));
				mav.setViewName(super.getPage("index.jsp"));
				HttpSession session = request.getSession();
				session.setAttribute("emp", emp);
				logger.info(msg); 
			} else
			{
				msg = "登陆失败";
				super.setForwardMessageAndUrl(mav, msg, super.getPage("login.jsp"));
				logger.info(msg);
			}
		} catch (Exception e)
		{
			logger.error(super.getMessage("unknown.error", "登陆"));
			logger.error(e.getMessage(), e);
			mav.setViewName(super.getPage("error.jsp"));
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
			
//			mav.setViewName(super.getPage("login.jsp"));
			String msg = super.getMessage("logout.success");
			super.setForwardMessageAndUrl(mav, msg, super.getPage("login.jsp"));
//			mav.addObject("msg",msg);
			logger.info(msg);
			return mav;
		}catch(Exception e)
		{
			String msg = super.getMessage("unknown.error", "注销");
			
			logger.error(msg);
			mav.addObject("msg",msg);
			mav.setViewName(super.getPage("error.jsp"));
			return mav;
		}
	}
}
