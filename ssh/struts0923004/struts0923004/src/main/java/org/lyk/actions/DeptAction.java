package org.lyk.actions;

import com.opensymphony.xwork2.ActionSupport;

public class DeptAction extends ActionSupport
{

	@Override
	public String execute() throws Exception
	{
		System.out.println("hello dept action");
		return SUCCESS;
	} 
}
