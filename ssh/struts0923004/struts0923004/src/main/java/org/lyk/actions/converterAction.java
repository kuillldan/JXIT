package org.lyk.actions;

import java.util.Arrays;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class converterAction extends ActionSupport
{
	private String[] hobi;
	public String[] getHobi()
	{
		return hobi;
	}
	
	@Override
	public void validate()
	{
		// TODO Auto-generated method stub
		super.validate();
	}
	
	public void setHobi(String[] hobi)
	{
		this.hobi = hobi;
	}
	
	
	public String insert()
	{ 
		System.out.println(Arrays.toString(this.hobi));
		return "success";
	}
}
