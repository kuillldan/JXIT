package org.lyk.vo;

import java.sql.Timestamp;

public class TimeDemo
{
	private Integer id;
	private String info;
	private Timestamp current;
	
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getInfo()
	{
		return info;
	}
	public void setInfo(String info)
	{
		this.info = info;
	}
	public Timestamp getCurrent()
	{
		return current;
	}
	public void setCurrent(Timestamp current)
	{
		this.current = current;
	}
	@Override
	public String toString()
	{
		return "TimeDemo [id=" + id + ", info=" + info + ", current=" + current + "]";
	} 
}
