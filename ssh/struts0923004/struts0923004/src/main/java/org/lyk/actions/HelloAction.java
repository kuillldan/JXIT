package org.lyk.actions;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class HelloAction extends ActionSupport
{
	private static final Logger logger = LoggerFactory.getLogger("logfile");
	
	@Override
	public String execute() throws Exception
	{
		logger.debug("HELLO SHIT");
		return super.execute();
	}
}
