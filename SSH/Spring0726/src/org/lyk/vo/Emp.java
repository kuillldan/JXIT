package org.lyk.vo;

import java.util.Date;

public class Emp
{
	private Integer empno;
	private String ename;
	private Double sal;
	private Date hiredate;
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
	public Date getHiredate()
	{
		return hiredate;
	}
	public void setHiredate(Date hiredate)
	{
		this.hiredate = hiredate;
	}
	@Override
	public String toString()
	{
		return "Emp [empno=" + empno + ", ename=" + ename + ", sal=" + sal + ", hiredate=" + hiredate + "]";
	} 
}
