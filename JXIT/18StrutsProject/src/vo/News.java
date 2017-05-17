package vo;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class News implements Serializable
{ 
	private Integer nid;
	private String title;
	private Date pubdate;
	private String content;
	 

	public Date getPubdate()
	{
		return pubdate;
	}



	public void setPubdate(Date pubdate)
	{
		this.pubdate = pubdate;
	}



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
 
	@Override
	public String toString()
	{
		return "News [nid=" + nid + ", title=" + title + ", pubdate=" + pubdate + ", content=" + content + "]";
	}


}