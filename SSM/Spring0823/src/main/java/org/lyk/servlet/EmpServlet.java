package org.lyk.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/emp/*")
public class EmpServlet
{
	private static final Logger logger = LoggerFactory.getLogger(EmpServlet.class);
	
	@RequestMapping("echo")
	public ModelAndView echo(String msg)
	{
		ModelAndView mav = new ModelAndView("/pages/emp/show.jsp");
		mav.addObject("msg",msg);
		logger.info("msg:" + msg);
		return mav;
	}
}
