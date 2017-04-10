package other;

import java.util.Date;

public class Company
{
	private String companyName;
	private Date credate;
	private Integer deptArray[];
	
	public Date getCredate()
	{
		return credate;
	}

	public void setCredate(Date credate)
	{
		this.credate = credate;
	}

	public Integer[] getDeptArray()
	{
		return deptArray;
	}

	public void setDeptArray(Integer[] deptArray)
	{
		this.deptArray = deptArray;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
	
	
}
