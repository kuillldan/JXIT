package org.lyk.vo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Dept
{
	private Integer deptno;
	private String dname;
	private String loc;
	
	private Properties addresses;
	
	@Override
	public String toString()
	{
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + ", addresses=" + addresses + "]";
	}
	public Properties getAddresses()
	{
		return addresses;
	}
	public void setAddresses(Properties addresses)
	{
		this.addresses = addresses;
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
