package org.lyk.actions;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lyk.service.IEmpService;
import org.lyk.utils.CommonConstant;
import org.lyk.utils.MD5Code;
import org.lyk.vo.Emp;
import org.lyk.vo.UserInfo;
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
		logger.debug("*****Action " + this.getClass().getSimpleName() + "创建");
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
				super.setForwardMessageAndUrl(mav, msg, super.getPage("index.jsp"));

				UserInfo userInfo = new UserInfo();
				userInfo.setEid(emp.getEid());
				userInfo.setAflag(emp.getAflag());
				userInfo.setName(emp.getName());
				userInfo.setPhoto(emp.getPhoto());

				HttpSession session = request.getSession();
				session.setAttribute(CommonConstant.USER_INFO, userInfo);
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
}
