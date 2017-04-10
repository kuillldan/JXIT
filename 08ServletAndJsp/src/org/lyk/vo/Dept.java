package org.lyk.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dept
{
	private Integer deptno;
	private String dname;
	private String loc;
	
	private Map<String, Object> stat;
	
	
	
	public Map<String, Object> getStat()
	{
		return stat;
	}
	public void setStat(Map<String, Object> stat)
	{
		this.stat = stat;
	}
	private List<Emp> allEmps = null;
	
	
	public List<Emp> getAllEmps()
	{
		return allEmps;
	}
	public void setAllEmps(List<Emp> allEmps)
	{
		this.allEmps = allEmps;
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
}
