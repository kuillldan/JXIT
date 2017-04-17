package vo;

import java.util.Date;

public class Goods
{
	//注意配置Goods与Item的关系(一对多)
	private Integer gid;
	private Item item = new Item();
	private String aid;
	private String title;
	private Date pubdate;
	private Double price;
	private Integer amount;
	private Integer bow;
	private String note;
	private String photo;
	private Integer status;
	
	public Integer getGid()
	{
		return gid;
	}
	public void setGid(Integer gid)
	{
		this.gid = gid;
	}
	 
	public Item getItem()
	{
		return item;
	}
	public void setItem(Item item)
	{
		this.item = item;
	}
	public String getAid()
	{
		return aid;
	}
	public void setAid(String aid)
	{
		this.aid = aid;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public Date getPubdate()
	{
		return pubdate;
	}
	public void setPubdate(Date pubdate)
	{
		this.pubdate = pubdate;
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
	public Integer getBow()
	{
		return bow;
	}
	public void setBow(Integer bow)
	{
		this.bow = bow;
	}
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note = note;
	}
	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo)
	{
		this.photo = photo;
	}
	public Integer getStatus()
	{
		return status;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	@Override
	public String toString()
	{
		return "Goods [gid=" + gid + ", item=" + item + ", aid=" + aid + ", title=" + title + ", pubdate="
				+ pubdate + ", price=" + price + ", amount=" + amount + ", bow=" + bow + ", text=" + note
				+ ", photo=" + photo + ", status=" + status + "]";
	} 
}
