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

	// array
	// private String[] groups;

	// list
	//private List<String> groups;

	// set
	//private Set<String> groups;
	
	// map
	//private Map<String, String> groups;
	// properties
	private Properties groups;

	public Integer getDeptno()
	{
		return deptno;
	}
	

	public Properties getGroups()
	{
		return groups;
	}


	public void setGroups(Properties groups)
	{
		this.groups = groups;
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

	 

	@Override
	public String toString()
	{
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", groups=" + groups + "]";
	} 

}
