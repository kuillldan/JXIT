package main;

import java.util.Objects;

public abstract class Person
{
	private String name;
	
	public Person(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public abstract String getDescription();
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof Person))
			return false;
		Person o = (Person)obj;
		
		return Objects.equals(this.name, o.name);

	}
}
