package org.lyk.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Comparable<Book>,Serializable
{
	private String name;
	private transient double price;
	

	public Book(String name,double price)
	{
		super();
		this.name = name;
		this.price = price;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		return false;
	}

	@Override
	public String toString()
	{
		return "Book [name=" + name + ", price=" + price + "]";
	}

	@Override
	public int compareTo(Book o)
	{
		if(this.price < o.price)
			return -1;
		else if(this.price > o.price)
			return 1;
		else {
			return 0;
		}
	}
}
