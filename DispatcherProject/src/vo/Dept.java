package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Dept implements Serializable
{
	private Integer did;
	private String dname;
	private Integer current;
	
	private Company company = new Company();
	
	public Integer getDid()
	{
		return did;
	}
	public void setDid(Integer did)
	{
		this.did = did;
	}
	public String getDname()
	{
		return dname;
	}
	public void setDname(String dname)
	{
		this.dname = dname;
	}
	public Integer getCurrent()
	{
		return current;
	}
	public void setCurrent(Integer current)
	{
		this.current = current;
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
		return "Dept [did=" + did + ", dname=" + dname + ", current=" + current + ", company=" + company + "]";
	}
}
