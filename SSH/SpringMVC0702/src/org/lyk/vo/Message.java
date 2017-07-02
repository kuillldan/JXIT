package org.lyk.vo;

import java.util.Date;

public class Message
{
	private Integer nid;
	private String title;
	private Date pubdate;
	private Double price;
	private Type type;
	
	
	
	public Type getType()
	{
		return type;
	}
	public void setType(Type type)
	{
		this.type = type;
	}
	public Integer getNid()
	{
		return nid;
	}
	public void setNid(Integer nid)
	{
		this.nid = nid;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public Date getPubdate()
	{
		return pubdate;
	}
	public void setPubdate(Date pubdate)
	{
		this.pubdate = pubdate;
	}
	public Double getPrice()
	{
		return price;
	}
	public void setPrice(Double price)
	{
		this.price = price;
	}
	@Override
	public String toString()
	{
		return "Message [nid=" + nid + ", title=" + title + ", pubdate="
				+ pubdate + ", price=" + price + ", type=" + type + "]";
	}  
}
