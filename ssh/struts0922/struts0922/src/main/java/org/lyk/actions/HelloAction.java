package org.lyk.actions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport
{
	private static final Logger logger = LoggerFactory.getLogger("logfile");
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "La vache quoi !!!";
	private String message;
	

	public String execute()
	{
		//org.apache.struts2.dispatcher.FilterDispatcher
		logger.debug("******logger testing*****");
		logger.error("******error message******");
		setMessage(MESSAGE);
		return "echo.jsp";
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
