package org.lyk.vo;

import java.util.Date;

public class Emp
{
	private Integer empno;
	private String ename;
	private Date hiredate;
	private Dept dept = new Dept();
	
	public Emp()
	{
		System.out.println("创建Emp -- " + Thread.currentThread().getName());
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

	public Date getHiredate()
	{
		return hiredate;
	}

	public void setHiredate(Date hiredate)
	{
		this.hiredate = hiredate;
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
		return "Emp [empno=" + empno + ", ename=" + ename + ", hiredate=" + hiredate + ", dept=" + dept + "]";
	} 
}
