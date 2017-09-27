package org.lyk.test;

import org.lyk.utils.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;

public class Hello
{
	public static void main(String[] args)
	{
		
		Logger logger = LoggerFactory.getLogger(CommonConstant.LOGFILE);
		logger.info("ssm0927 project");
	}
}
