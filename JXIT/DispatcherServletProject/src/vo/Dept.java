package vo;

import java.util.Arrays;

public class Dept
{
	private String dname;
	private Integer deptno;
	private String address;
	private String[] locs;
	private Company company = new Company();
	
	
	
	
	
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getDname()
	{
		return dname;
	}
	public void setDname(String dname)
	{
		this.dname = dname;
	}
	
	
	
	public Integer getDeptno()
	{
		return deptno;
	}
	public void setDeptno(Integer deptno)
	{
		this.deptno = deptno;
	}
	public String[] getLocs()
	{
		return locs;
	}
	public void setLocs(String[] locs)
	{
		this.locs = locs;
	}
	public Company getCompany()
	{
		return company;
	}
	public void setCompany(Company company)
	{
		this.company = company;
	}
	@Override
	public String toString()
	{
		return "Dept [dname=" + dname + ", deptno=" + deptno + ", address=" + address + ", locs="
				+ Arrays.toString(locs) + ", company=" + company + "]";
	}
}
