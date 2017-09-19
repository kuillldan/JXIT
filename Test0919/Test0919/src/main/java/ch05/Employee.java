package ch05;

import java.time.LocalDate;

public class Employee 
{ 
	private String name;
	private Double salary;
	private LocalDate hireDate;
	
	public Employee(String name, Double salary, Integer year,Integer month,Integer day)
	{
		this.name = name;
		this.salary = salary;
		this.hireDate = LocalDate.of(year, month, day);
	} 
	
	public Double getSalary()
	{
		return salary;
	}
 
	public String getDescription()
	{ 
		return "an employee with a salary of $" + this.getSalary();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hireDate == null) ? 0 : hireDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		if (this.getClass() != obj.getClass())
			return false;
		
		Employee other = (Employee) obj;
		
		if (hireDate == null)
		{
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (salary == null)
		{
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		
		return true;
	} 
}
