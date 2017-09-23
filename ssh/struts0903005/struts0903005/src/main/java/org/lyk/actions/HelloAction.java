package org.lyk.actions;

import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class HelloAction extends ActionSupport
{
	private static final Logger logger = LoggerFactory.getLogger("logfile");
	
	@Override
	public String execute() throws Exception
	{ 
		System.out.println(super.getText("info.msg",new String[]{"hello","world"}));
		return SUCCESS;
	}
}
