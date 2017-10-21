package org.lyk.actions;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;

public abstract class AbstractController
{
	@Resource
	private MessageSource messageSource;
	public String getMessage(String key,String ... params)
	{
		return this.messageSource.getMessage(key, params, Locale.getDefault());
	}
}
