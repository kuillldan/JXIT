package org.lyk.actions;

import java.util.Locale;

import javax.annotation.Resource;

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
	
	protected String getMessage(String key,Object ... params)
	{
		return this.getString(key, params);
	}
	
	protected String getPage(String key,Object ... params)
	{
		return this.getString(key, params);
	}
	
	protected String getValidation(String key,Object ... params)
	{
		return this.getString(key, params);
	}
	
	protected void setForwardMessageAndUrl(ModelAndView mav,String msg, String url)
	{
		mav.setViewName(this.getPage("forward.jsp"));
		mav.addObject("msg",msg);
		mav.addObject("url",url);
	}
}
