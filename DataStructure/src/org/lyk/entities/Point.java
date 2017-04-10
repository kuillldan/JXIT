package org.lyk.entities;
 
public class Point 
{
	private String x;
	private String y;
	public Point(String x, String y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void set(String x)
	{
		this.x = x;
	}
	
	public String getX()
	{
		return x;
	}
	public void setX(String x)
	{
		this.x = x;
	}
	public String getY()
	{
		return y;
	}
	public void setY(String y)
	{
		this.y = y;
	}
	
	public static <T> T fun(T param)
	{
		System.out.println(param);
		return param;
	}
	
	public void display()
	{
		System.out.println(this.toString());
	}
	
	public String getInfo()
	{
		return this.x + "," + this.y; 
	}
}
