package org.lyk.vo;

public class Emp
{
	private String name;
	private Dept dept = new Dept();

	
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Dept getDept()
	{
		return dept;
	}

	public void setDept(Dept dept)
	{
		this.dept = dept;
	}

	@Override
	public String toString()
	{
		return "Emp [name=" + name + ", dept=" + dept + "]";
	}

		
}
