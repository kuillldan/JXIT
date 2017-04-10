package org.lyk.vo;

import java.util.Date;

public class Emp
{
	private Integer empno;
	private String ename;
	private String job;
	private Emp mgr;
	private Date hiredate;
	private Integer sal;
	private Integer comm;
	private Dept dept;
	
	
	private String photo;
	private String note;
	
	
	
	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo)
	{
		this.photo = photo;
	}
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note = note;
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
	public String getJob()
	{
		return job;
	}
	public void setJob(String job)
	{
		this.job = job;
	}
	 
	public Date getHiredate()
	{
		return hiredate;
	}
	public void setHiredate(Date hiredate)
	{
		this.hiredate = hiredate;
	}
	public Integer getSal()
	{
		return sal;
	}
	public void setSal(Integer sal)
	{
		this.sal = sal;
	}
	public Integer getComm()
	{
		return comm;
	}
	public void setComm(Integer comm)
	{
		this.comm = comm;
	}
	public Emp getMgr()
	{
		return mgr;
	}
	public void setMgr(Emp mgr)
	{
		this.mgr = mgr;
	}
	public Dept getDept()
	{
		return dept;
	}
	public void setDept(Dept dept)
	{
		this.dept = dept;
	} 
}
