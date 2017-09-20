package ch05;

import java.time.LocalDate;
import java.util.Objects;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Employee implements Comparable<Employee>
{

	private String name;
	private Double salary;
	private LocalDate hireDate;

	private Runnable r = () ->
	{
		System.out.println(name);
	};

	public Employee()
	{
		// TODO Auto-generated constructor stub

	}

	public Employee(String name, Double salary, Integer year, Integer month, Integer day)
	{
		this.name = name;
		this.salary = salary;
		this.hireDate = LocalDate.of(year, month, day);
	}

	public void show(String msg)
	{ 
		class AAA
		{
			public void show()
			{
				System.out.println(msg);
				System.out.println(Employee.this.name);
			}
		}
		
		
	}

	public Double getSalary()
	{
		class AAA
		{
			static final String sdff = "";
		}
		Runnable rr = new Runnable()
		{
			static final String sdfasf="";
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				
			}
		};
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

		if (!Objects.equals(this.hireDate, other.hireDate))
			return false;

		if (!Objects.equals(this.name, other.name))
			return false;

		if (!Objects.equals(this.salary, other.salary))
			return false;

		return true;
	}

	@Override
	public String toString()
	{
		return this.getClass().getName() + " [name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + "]";
	}

	public String getName()
	{
		return name;
	}

	@Override
	public final int compareTo(Employee o)
	{
		if (!(o instanceof Employee))
			throw new ClassCastException();

		Employee e = (Employee) o;

		if (this.salary == null && e.salary == null)
			return 0;

		if (this.salary == null && e.salary != null)
			return -1;

		if (this.salary != null && e.salary == null)
			return 1;

		if (this.salary != null && e.salary != null)
			return this.salary.compareTo(e.salary);

		return 0;
	}
}
