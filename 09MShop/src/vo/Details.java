package vo;

public class Details
{ 
	private Integer odid;
	private Orders order ;
	private Goods goods;
	private String title;
	private Double price;
	private Integer amount;
	
	
	
	
	public Integer getOdid()
	{
		return odid;
	}
	public void setOdid(Integer odid)
	{
		this.odid = odid;
	}
	public Orders getOrder()
	{
		return order;
	}
	public void setOrder(Orders order)
	{
		this.order = order;
	}
	public Goods getGoods()
	{
		return goods;
	}
	public void setGoods(Goods goods)
	{
		this.goods = goods;
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
	public Integer getAmount()
	{
		return amount;
	}
	public void setAmount(Integer amount)
	{
		this.amount = amount;
	}
	@Override
	public String toString()
	{
		return "Details [order=" + order + ", goods=" + goods + ", title=" + title + ", price=" + price
				+ ", amount=" + amount + "]";
	} 
}
