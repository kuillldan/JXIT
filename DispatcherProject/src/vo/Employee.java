package vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Employee implements Serializable
{
	private Integer empno;
	private String ename;
	private Double sal;
	private Date hiredate;
	
	private Dept dept = new Dept();
	public String getEname()
	{
		return ename;
	}
	
	
	public Integer getEmpno()
	{
		return empno;
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


	public void setEmpno(Integer empno)
	{
		this.empno = empno;
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