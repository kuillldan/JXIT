package org.lyk.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Emp implements Serializable
{
	private Integer empno;
	private String ename;
	private Double sal;
	private Dept dept;
	
	
	
	public Dept getDept()
	{
		return dept;
	}
	public void setDept(Dept dept)
	{
		this.dept = dept;
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
	public Double getSal()
	{
		return sal;
	}
	public void setSal(Double sal)
	{
		this.sal = sal;
	}
	@Override
	public String toString()
	{
		return "Emp [empno=" + empno + ", ename=" + ename + ", sal=" + sal
				+ ", dept=" + dept + "]";
	}
}
