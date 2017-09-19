package ch05;

public class Manager extends Employee
{
	private Double bouns;
	
	public Manager(String name, Double salary, Integer year, Integer month, Integer day)
	{
		super(name, salary, year, month, day); 
		this.bouns = 0.0;
	}
	
	public Double getBouns()
	{
		return this.bouns;
	}
	
	public void setBouns(Double bonus)
	{
		this.bouns = bonus;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bouns == null) ? 0 : bouns.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
	
		if (super.getClass() != obj.getClass())
			return false;
		
		Manager other = (Manager) obj;
		
		if(!super.equals(obj))
			return false;
		
		if (bouns == null)
		{
			if (other.bouns != null)
				return false;
		} else if (!bouns.equals(other.bouns))
			return false;
		
		return true;
		 
	}
	
	
	
}
