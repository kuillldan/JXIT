package org.lyk.vo;
 
public class Dept
{
	private String dname;
	private String loc;
	
	
	public String getDname()
	{
		return dname;
	}

	public void setDname(String dname)
	{
		this.dname = dname;
	}

	public String getLoc()
	{
		return loc;
	}

	public void setLoc(String loc)
	{
		this.loc = loc;
	}

	public Dept() throws NumberFormatException,NullPointerException
	{
		System.out.println("无参构造器函数");
	}
	
	public Dept(String dname,Integer years)throws ClassNotFoundException
	{
		System.out.println("有参构造函数");
	}
	
	
	private void funcA()
	{
		System.out.println("===funcA");
	}
	
	public void funcB()
	{
		System.out.println("===funcB");
		
	}

	@Override
	public String toString()
	{
		return "Dept [dname=" + dname + ", loc=" + loc + "]";
	}
	
	
}
