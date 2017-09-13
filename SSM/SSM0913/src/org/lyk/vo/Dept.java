package org.lyk.vo;

import org.lyk.util.LoggerFactory;

public class Dept
{
	private Integer deptno;
	private String dname;
	private String loc;
	
	public Dept()
	{
		LoggerFactory.getLogger().info("*****dept initialize*****");;
	}
	
	public Integer getDeptno()
	{
		return deptno;
	}
	public void setDeptno(Integer deptno)
	{
		this.deptno = deptno;
	}
	public String getDname()
	{
		return dname;
	}
	public void setDname(String dname)
	{
		this.dname = dname;
	}
	public String getLoc()
	{
		return loc;
	}
	public void setLoc(String loc)
	{
		this.loc = loc;
	}
	@Override
	public String toString()
	{
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	} 
}
