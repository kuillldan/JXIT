package org.lyk.vo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Emp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "emp", catalog = "mldn")
public class Emp implements java.io.Serializable
{

	// Fields

	private Integer empno;
	private Dept dept;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hiredate;
	private Integer sal;
	private Integer comm;
	private String photo;
	private String note;

	// Constructors

	/** default constructor */
	public Emp()
	{
	}

	/** minimal constructor */
	public Emp(Integer empno)
	{
		this.empno = empno;
	}

	/** full constructor */
	public Emp(Integer empno, Dept dept, String ename, String job, Integer mgr, Date hiredate, Integer sal,
			Integer comm, String photo, String note)
	{
		this.empno = empno;
		this.dept = dept;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.photo = photo;
		this.note = note;
	}

	// Property accessors
	@Id
	@Column(name = "EMPNO", unique = true, nullable = false)
	public Integer getEmpno()
	{
		return this.empno;
	}

	public void setEmpno(Integer empno)
	{
		this.empno = empno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTNO")
	public Dept getDept()
	{
		return this.dept;
	}

	public void setDept(Dept dept)
	{
		this.dept = dept;
	}

	@Column(name = "ENAME", length = 10)
	public String getEname()
	{
		return this.ename;
	}

	public void setEname(String ename)
	{
		this.ename = ename;
	}

	@Column(name = "JOB", length = 9)
	public String getJob()
	{
		return this.job;
	}

	public void setJob(String job)
	{
		this.job = job;
	}

	@Column(name = "MGR")
	public Integer getMgr()
	{
		return this.mgr;
	}

	public void setMgr(Integer mgr)
	{
		this.mgr = mgr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HIREDATE", length = 0)
	public Date getHiredate()
	{
		return this.hiredate;
	}

	public void setHiredate(Date hiredate)
	{
		this.hiredate = hiredate;
	}

	@Column(name = "SAL")
	public Integer getSal()
	{
		return this.sal;
	}

	public void setSal(Integer sal)
	{
		this.sal = sal;
	}

	@Column(name = "COMM")
	public Integer getComm()
	{
		return this.comm;
	}

	public void setComm(Integer comm)
	{
		this.comm = comm;
	}

	@Column(name = "photo", length = 50)
	public String getPhoto()
	{
		return this.photo;
	}

	public void setPhoto(String photo)
	{
		this.photo = photo;
	}

	@Column(name = "note", length = 1024)
	public String getNote()
	{
		return this.note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

}