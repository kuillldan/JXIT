package vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Employee implements Serializable
{
	private String ename;
	private Dept dept = new Dept();
	public String getEname()
	{
		return ename;
	}
	public void setEname(String ename)
	{
		this.ename = ename;
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
		return "Employee [ename=" + ename + ", dept=" + dept + "]";
	}
}