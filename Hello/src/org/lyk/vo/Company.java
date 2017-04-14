package org.lyk.vo;

public class Company
{
	private String name;
	private Integer companyCode;
 
	public Integer getCompanyCode()
	{
		return companyCode;
	}

	public void setCompanyCode(Integer companyCode)
	{
		this.companyCode = companyCode;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Company [name=" + name + ", companyCode=" + companyCode + "]";
	}
 }
