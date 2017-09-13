package org.lyk.action;

//import javax.annotation.Resource;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import org.lyk.util.ResourceReader;
import org.lyk.vo.Dept;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test/*")
public class Test
{
	private static Logger logger = org.lyk.util.LoggerFactory.getLogger();

	@javax.annotation.Resource(name = "messageReader")
	private MessageSource messageReader;

	@RequestMapping("testDemo")
	public ModelAndView testDemo(String msg)
	{
		String userIdPrefixPath = this.messageReader.getMessage("USER_ID_PREFIX", null, Locale.getDefault());
		logger.info("userIdPrefixPath:" + userIdPrefixPath);
		InputStream is = ResourceReader.getInputStream("file:" + userIdPrefixPath);
		String userIdPrefix = this.getUserIdPrefix(is);
		ResourceReader.close(is);
		logger.info("userIdPrefix:" + userIdPrefix);

		ModelAndView mav = new ModelAndView("/index.jsp");
		mav.addObject("msg", msg);
		return mav;
	}

	private String getUserIdPrefix(InputStream is)
	{
		Scanner scanner = new Scanner(is);
		String userIdPrefix = null;
		if(scanner.hasNextLine())
		{
			userIdPrefix = scanner.next();
		}
		if(scanner != null)
			scanner.close();
		
		return userIdPrefix;
	}
}
