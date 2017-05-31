package lyk.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Emp implements Serializable
{
	private Integer empno;
	private String ename;
	private Dept dept;
	
	
	public Emp()
	{
		System.out.println("Emp 创建");
		System.out.println(this.toString());
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer getEmpno()
	{
		return empno;
	}
	public void setEmpno(Integer empno)
	{
		this.empno = empno;
	}
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
		return "Emp [empno=" + empno + ", ename=" + ename + ", dept=" + dept + "]";
	} 
}
