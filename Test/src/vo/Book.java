package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable
{
	private String title;
	private Double price;
	
	public Book()
	{
		// TODO Auto-generated constructor stub
	}
	
	public Book(String title, Double price)
	{
		super();
		this.title = title;
		this.price = price;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public Double getPrice()
	{
		return price;
	}
	public void setPrice(Double price)
	{
		this.price = price;
	}

	@Override
	public String toString()
	{
		return "Book [title=" + title + ", price=" + price + "]";
	}
}
