package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Item implements Serializable
{
	private Integer iid;
	private String title;
	private OtherInfo otherInfo = new OtherInfo(); 
	 
	public OtherInfo getOtherInfo()
	{
		return otherInfo;
	}
	public void setOtherInfo(OtherInfo otherInfo)
	{
		this.otherInfo = otherInfo;
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
		return "Item [iid=" + iid + ", title=" + title + ", otherInfo=" + otherInfo + "]";
	}
	
}
