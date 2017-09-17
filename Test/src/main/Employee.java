package main;

import java.time.LocalDate;
import java.util.Objects;

public class Employee extends Person
{
	private Double salary;
	private LocalDate hireDay;

	public Employee(String name, Double salary, Integer year, Integer month, Integer day)
	{
		super(name);
		this.salary = salary;
		this.hireDay = LocalDate.of(year, month, day);
	}

	public Double getSalary()
	{
		return salary;
	}

	public LocalDate getHireDay()
	{
		return hireDay;
	}

	@Override
	public String getDescription()
	{
		return String.format("an employee with a salary of $%.2f", this.salary);
	}

	public void raiseSalary(Double byPercent)
	{
		Double raise = this.salary * byPercent / 100;
		this.salary += raise;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (this.getClass() != obj.getClass())
			return false;
		Employee e = (Employee) obj;
		return super.equals(e) && 
				Objects.equals(this.hireDay, e.hireDay) && 
				Objects.equals(this.salary, e.salary);
	}

}
