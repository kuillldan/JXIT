package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ShopCar implements Serializable
{
	private Member member;
	private Goods goods;
	private Integer amount;
	
	public Member getMember()
	{
		return member;
	}
	public void setMember(Member member)
	{
		this.member = member;
	}
	public Goods getGoods()
	{
		return goods;
	}
	public void setGoods(Goods goods)
	{
		this.goods = goods;
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
		return "ShopCar [member=" + member + ", goods=" + goods + ", amount=" + amount + "]";
	}
	 
	
}
