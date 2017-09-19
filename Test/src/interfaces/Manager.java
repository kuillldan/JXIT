package interfaces;

public class Manager extends Employee
{
	private Double bonus;
	
	public Manager(String name, Double salary,Double bonus)
	{
		super(name, salary);
		this.bonus = bonus;
	}

	@Override
	public int compareTo(Employee o)
	{
		if(this.getClass() != o.getClass())
			throw new ClassCastException();
		
		Manager m = (Manager)o;
		
		return Double.compare(this.bonus, m.bonus);
	} 
}
