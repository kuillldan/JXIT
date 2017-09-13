package org.lyk.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/test/*")
public class Test
{
	private Logger logger = LoggerFactory.getLogger("logfile");
	@RequestMapping("testDemo")
	public ModelAndView testDemo(String msg)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		logger.info("This is for log file " + sdf.format(new Date()));
		logger.error("This is for sys log file " + sdf.format(new Date()));
		return new ModelAndView("/index.jsp");
	}
}
