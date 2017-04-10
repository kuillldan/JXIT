package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Item implements Serializable
{
	private Integer iid;
	private String title;
	
	private Goods goods;
	
	
	public Goods getGoods()
	{
		return goods;
	}
	public void setGoods(Goods goods)
	{
		this.goods = goods;
	}
	public Integer getIid()
	{
		return iid;
	}
	public void setIid(Integer iid)
	{
		this.iid = iid;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	@Override
	public String toString()
	{
		return "Item [iid=" + iid + ", title=" + title + ", goods=" + goods + "]";
	}  
}
