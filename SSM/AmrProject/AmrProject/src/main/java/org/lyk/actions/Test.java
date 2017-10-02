package org.lyk.actions;

import org.lyk.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages/test/*")
public class Test
{
	private static final Logger logger = LoggerFactory.getLogger(CommonConstant.LOGFILE);
	@RequestMapping("doTest")
	public void doTest(String msg)
	{
		logger.info("传入消息:" + msg);
	}
}
