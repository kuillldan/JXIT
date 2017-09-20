package ch05;

import java.time.LocalDate;
import java.util.Objects;

public class Employee<T>
{ 
	private T t;
	
	private String name;
	private Double salary;
	private LocalDate hireDate;
	
	public void set(T t)
	{
		this.t = t;
	}
	
	public Employee()
	{
		// TODO Auto-generated constructor stub
	}
	
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
		
		if(!Objects.equals(this.hireDate, other.hireDate))
			return false;
		
		if(!Objects.equals(this.name, other.name))
			return false;
		
		if(!Objects.equals(this.salary, other.salary))
			return false;
		
		return true;
	}

	@Override
	public String toString()
	{
		return this.getClass().getName() + " [name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + "]";
	} 
	
	public T getT()
	{
		return this.t;
	}
	
}
