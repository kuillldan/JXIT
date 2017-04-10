package org.lyk.entities;

public enum Color
{
	RED("����ɫ"),BLUE("��ԭɫ"),GREEN("����ɫ");
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
