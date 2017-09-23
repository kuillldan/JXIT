package org.lyk.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class HelloAction extends ActionSupport
{

	@Override
	public String execute() throws Exception
	{
		Logger logger = LoggerFactory.getLogger("logfile");
		logger.debug("*****hello debug*****");
		logger.info("*****hello info*****");
		logger.error("*****hello error*****");
		return super.execute();
	}
}
