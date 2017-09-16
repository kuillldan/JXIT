package org.lyk.action;

import org.lyk.vo.Dept;

//import javax.annotation.Resource;

 
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test/*")
public class Test
{
	private static Logger logger = LoggerFactory.getLogger("logfile");
  
	@RequestMapping("testDemo")
	public ModelAndView testDemo(String msg,Dept dept)
	{
		logger.info("msg:" + msg);
		logger.info(dept.toString());
		

		ModelAndView mav = new ModelAndView("/index.jsp");
		mav.addObject("msg", msg);
		return mav;
	} 
}
