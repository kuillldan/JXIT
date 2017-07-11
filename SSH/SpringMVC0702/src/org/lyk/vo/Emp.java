package org.lyk.vo;

import java.util.Date;

public class Emp
{
	private Integer empno;
	private String ename;
	private Double salary;
	private Date dateOfBirthday;
	
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

	public Double getSalary()
	{
		return salary;
	}

	public void setSalary(Double salary)
	{
		this.salary = salary;
	}

	public Date getDateOfBirthday()
	{
		return dateOfBirthday;
	}

	public void setDateOfBirthday(Date dateOfBirthday)
	{
		this.dateOfBirthday = dateOfBirthday;
	}

	@Override
	public String toString()
	{
		return "Emp [empno=" + empno + ", ename=" + ename + ", salary=" + salary + ", dateOfBirthday="
				+ dateOfBirthday + ", dept=" + dept + "]";
	}
}
