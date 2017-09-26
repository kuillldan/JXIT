package org.lyk.vo;

import java.io.Serializable;
import java.util.List;

public class Type implements Serializable
{
	private Integer tid;
	private String title;
	private List<SubType> subTypes;
	
	public Type()
	{
		// TODO Auto-generated constructor stub
	}
	
	

	public List<SubType> getSubTypes()
	{
		return subTypes;
	}



	public void setSubTypes(List<SubType> subTypes)
	{
		this.subTypes = subTypes;
	}



	public Integer getTid()
	{
		return tid;
	}
	public void setTid(Integer tid)
	{
		this.tid = tid;
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
		return "Type [tid=" + tid + ", title=" + title + "]";
	} 
}