package org.lyk.actions;

import com.opensymphony.xwork2.ActionSupport;

public class DeptAction extends ActionSupport
{

	@Override
	public String execute() throws Exception
	{
		System.out.println("**********execute执行");
		return SUCCESS;
	}
	
	@Override
	public void validate()
	{ 
		System.out.println("**********validate执行");
	}
}
