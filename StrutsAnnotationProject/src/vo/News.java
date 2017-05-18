package vo;

import java.util.Date;

public class News
{
	private Integer nid;
	private String title;
	private String content;
	private Date pubdate;
	public Integer getNid()
	{
		return nid;
	}
	public void setNid(Integer nid)
	{
		this.nid = nid;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getPubdate()
	{
		return pubdate;
	}
	public void setPubdate(Date pubdate)
	{
		this.pubdate = pubdate;
	}
	@Override
	public String toString()
	{
		return "News [nid=" + nid + ", title=" + title + ", content=" + content + ", pubdate=" + pubdate + "]";
	}
}
