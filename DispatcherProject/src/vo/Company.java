package vo;

import java.util.Arrays;
import java.util.Date;

public class Company
{
	private String name;
	private String addr;
	private String foundDate;
	private Integer[] subCompany;
	
	
	
	public Integer[] getSubCompany()
	{
		return subCompany;
	}
	public void setSubCompany(Integer[] subCompany)
	{
		this.subCompany = subCompany;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getAddr()
	{
		return addr;
	}
	public void setAddr(String addr)
	{
		this.addr = addr;
	}
	public String getFoundDate()
	{
		return foundDate;
	}
	public void setFoundDate(String foundDate)
	{
		this.foundDate = foundDate;
	}
	@Override
	public String toString()
	{
		return "Company [name=" + name + ", addr=" + addr + ", foundDate=" + foundDate + ", subCompany="
				+ Arrays.toString(subCompany) + "]";
	}
}
