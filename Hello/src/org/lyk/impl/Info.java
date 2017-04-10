package org.lyk.impl;

import java.math.BigInteger;

public class Info
{
	private String name;
	private String address;
	private Integer age; 
	
	
	public Info(String name, String address, Integer age)
	{
		super();
		this.name = name;
		this.address = address;
		this.age = age;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
//	@Override
//	public int hashCode()
//	{
//		final int prime = 27;
//		int result = 1;
//		result = prime*result + (this.name == null ? 0:this.name.hashCode());
//		result = prime*result + (this.address == null ? 0:this.address.hashCode());
//		result = prime*result + (this.age == null ? 0 : this.age.hashCode());
//		return result;
//	}
	
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Info other = (Info) obj;
		if (address == null)
		{
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age == null)
		{
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
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
	public Integer getAge()
	{
		return age;
	}
	public void setAge(Integer age)
	{
		this.age = age;
	}
	@Override
	public String toString()
	{
		return "Info [name=" + name + ", address=" + address + ", age=" + age
				+ "]";
	}
	
	
	
	
}
