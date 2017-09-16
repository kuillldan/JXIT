package org.lyk.action;

import javax.annotation.Resource;

import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/dept/*")
public class DeptAction
{
	private Logger logger = LoggerFactory.getLogger("logfile");
	
	@Resource(name = "deptServiceImpl")
	private IDeptService deptServiceImpl;

	@RequestMapping("insert")
	public ModelAndView insert(Dept dept)
	{
		try
		{
			logger.info(dept.toString());
			String msg = "Dept信息插入成功";
			if(this.deptServiceImpl.insert(dept))
			{
				msg = "Dept信息插入成功";
				logger.info(msg);
			}
			else
			{
				msg = "Dept信息插入失败";
				logger.info(msg);
			}
			
			ModelAndView mav = new ModelAndView("/pages/dept/show.jsp");
			mav.addObject("msg",msg);
			return mav;
		}
		catch(Exception e)
		{
			logger.error("系统错误,请联系管理员");
			logger.error(e.getMessage(),e);
			return new ModelAndView("/pages/common/error.jsp");
		}
	}
}
