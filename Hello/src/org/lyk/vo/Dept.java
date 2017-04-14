package org.lyk.vo;

import java.io.Serializable;
 
@SuppressWarnings("serial")
public class Dept implements Serializable,Cloneable
{
	private Company company = new Company();

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
		return "Dept [company=" + company + "]";
	}
	
	
}
