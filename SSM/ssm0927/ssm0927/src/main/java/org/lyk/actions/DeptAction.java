package org.lyk.actions;

import javax.annotation.Resource;

import org.lyk.service.IDeptService;
import org.lyk.utils.CommonConstant;
import org.lyk.vo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/dept/*")
public class DeptAction
{
	private static final Logger logger = LoggerFactory.getLogger(CommonConstant.LOGFILE);
	
	@Resource(name = "deptServiceImpl")
	private IDeptService deptServiceImpl;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@RequestMapping("insert")
	public ModelAndView insert(Dept dept)
	{
		ModelAndView mav = new ModelAndView(CommonConstant.PAGE_FORWARD);

		try
		{
			if (this.deptServiceImpl.insert(dept))
			{
				mav.addObject("msg", "部门数据增加成功");
				mav.addObject("url", "/pages/dept/dept_insert.jsp");
				logger.info("部门数据增加成功");
			} else
			{
				mav.addObject("msg", "部门数据增加失败");
				mav.addObject("url", "/pages/dept/dept_insert.jsp");
				logger.info("部门数据增加失败");
			}
		} catch (Exception e)
		{
			logger.error("部门数据增加失败");
			logger.error(e.getMessage(),e);
			mav.setViewName(CommonConstant.PAGE_ERROR);
		}
		return mav;
	}
}
