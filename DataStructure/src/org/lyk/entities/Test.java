package org.lyk.entities;
class A
{
	private String a1;
	public String a2;
}

public class Test extends A 
{  
	private String name;
	private String desc;
	public String infos;
	
	private Test()
	{}
	
	public Test(String name, String desc)
	{
		this.name = name;
		this.desc = desc;
	}
	public String getName()
	{
		return name;
	}
	public String getDesc()
	{
		return desc;
	} 
	
	private void printInternal()
	{};
	 
}
