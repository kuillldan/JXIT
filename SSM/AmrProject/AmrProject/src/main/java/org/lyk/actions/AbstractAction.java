package org.lyk.actions;

import java.util.Locale;

import javax.annotation.Resource;

import org.lyk.utils.CommonConstant;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AbstractAction
{
	@Resource
	private MessageSource messageSource;
	
	private String getString(String key,Object ... params)
	{
		return this.messageSource.getMessage(key, params, Locale.getDefault());
	}
	
	public String getMessage(String key,Object ... params)
	{
		return this.getString(key, params);
	}
	
	public String getPage(String key)
	{
		return this.getString(key);
	}
	
	public String getValidation(String key)
	{
		return this.getString(key );
	}
	
	protected void setForwardMessageAndUrl(ModelAndView mav,String msg, String url)
	{
		mav.setViewName(this.getPage("forward.jsp"));
		mav.addObject("msg",msg);
		mav.addObject("url",url);
	}
	
	protected void setSystemError(ModelAndView mav, String msg,Exception e)
	{
		mav.setViewName(this.getPage(CommonConstant.ERROR_JSP));
		mav.addObject(CommonConstant.MSG,msg);
		CommonConstant.LOGGER.error(msg);
		CommonConstant.LOGGER.error(e.getMessage(),e);
	}
}
