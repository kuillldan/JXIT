package tutorial;

import org.apache.struts2.dispatcher.FilterDispatcher;
//import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.slf4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class HelloAction extends ActionSupport
{
	// private static final Logger logger =
	// org.slf4j.LoggerFactory.getLogger("logfile");
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "La vache quoi !!!";
	private String message;

	public String execute()
	{
		 Logger logger = org.slf4j.LoggerFactory.getLogger("logfile");
		 logger.debug("*******hello shit*******");
		 logger.info("*******hello shit*******");
		 logger.error("*******hello shit*******");
		setMessage(MESSAGE);
		return SUCCESS;
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
