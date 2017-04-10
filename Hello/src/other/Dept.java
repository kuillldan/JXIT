package other;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Dept implements Serializable
{
	private Integer deptno;
	private String dname;
	private Company company = new Company();
	
	
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
	public Company getCompany()
	{
		return company;
	}
	public void setCompany(Company company)
	{
		this.company = company;
	}
	
	
	
}
