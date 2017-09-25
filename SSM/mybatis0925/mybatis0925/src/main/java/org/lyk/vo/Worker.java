package org.lyk.vo;

public class Worker extends Member
{
	private String company;
	private Double salary;
	
	public Worker()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString()
	{
		return "Worker [company=" + company + ", salary=" + salary + "]";
	}
	public String getCompany()
	{
		return company;
	}
	public void setCompany(String company)
	{
		this.company = company;
	}
	public Double getSalary()
	{
		return salary;
	}
	public void setSalary(Double salary)
	{
		this.salary = salary;
	}
	
}
