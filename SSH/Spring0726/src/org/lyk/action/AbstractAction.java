package org.lyk.action;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class AbstractAction
{
	@InitBinder
	public void dataBinder(WebDataBinder webDataBinder)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		webDataBinder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));
	}
}
