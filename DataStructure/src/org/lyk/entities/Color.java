package org.lyk.entities;

public enum Color
{
	RED("红颜色"),BLUE("蓝原色"),GREEN("绿颜色");
	private Color()
	{
		// TODO Auto-generated constructor stub
	}
	
	private String desc;
	private Color(String desc)
	{
		this.desc = desc;
	}
	
	@Override
	public String toString()
	{
		return super.ordinal() + ":" + super.name() + "("+this.desc+")";
	}
}
