package ch08;

public class Employee
{
	private String name;
	private String address;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	@Override
	public String toString()
	{
		return "Employee [name=" + name + ", address=" + address + "]";
	}
	@Override
	public int hashCode()
	{
		return 1;
	} 
}
