package org.lyk.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SubType implements Serializable
{
	private Integer stid;
	private String title;
	
	private Type type;

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public SubType()
	{
		// TODO Auto-generated constructor stub
	}

	public Integer getStid()
	{
		return stid;
	}

	public void setStid(Integer stid)
	{
		this.stid = stid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Override
	public String toString()
	{
		return "SubType [stid=" + stid + ", title=" + title + "]";
	} 
}
