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
@RequestMapping(value="/pages/dept/*")
public class DeptAction
{
	private static final Logger logger = LoggerFactory.getLogger(DeptAction.class);
	
	@Resource(name="deptServiceImpl")
	private IDeptService deptServiceImpl;
	
	@RequestMapping(value="insert")
	public ModelAndView insert(Dept dept)
	{
		ModelAndView mav = new ModelAndView("/pages/dept/show.jsp");
		logger.info("【控制层】接收到Dept数据:" + dept);
		if(this.deptServiceImpl.insert(dept))
		{
			mav.addObject("msg","数据增加成功");
		}
		else
		{
			mav.addObject("msg","数据增加失败");
		}
		
		return mav;
	}
}
