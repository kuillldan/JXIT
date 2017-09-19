package interfaces;

import java.io.Serializable;

public class Employee implements Comparable<Employee>,Serializable
{
	private String name;
	private Double salary;
	
	public static void show()
	{
		System.out.println("static import demo");
	}

	public Employee(String name, Double salary)
	{
		super();
		this.name = name;
		this.salary = salary;
	}

	public void raiseSalary(Double byPercent)
	{
		Double raise = this.salary * byPercent / 100;
		this.salary += raise;
	}

	public String getName()
	{
		return name;
	}
	
	public String aaa()
	{
		return "xfds";
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Double getSalary()
	{
		return salary;
	}

	public void setSalary(Double salary)
	{
		this.salary = salary;
	}

	@Override
	public int compareTo(Employee o)
	{
		return Double.compare(this.salary, o.salary);
	}

}
