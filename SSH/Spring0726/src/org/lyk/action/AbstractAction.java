package org.lyk.action;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


@Service
public abstract class AbstractAction
{
	@Resource
	private MessageSource messageSource;

	public String getMessage(String key, Object[] params)
	{
		return this.messageSource.getMessage(key, params, Locale.getDefault());
	}

	@InitBinder
	public void dataBinder(WebDataBinder webDataBinder)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		webDataBinder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));
	}
}
