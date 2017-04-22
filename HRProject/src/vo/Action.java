package vo;

import java.io.Serializable;

//权限

@SuppressWarnings("serial")
public class Action implements Serializable
{
	private Integer actid;
	private Groups groups;
	private String title;
	private String url;
	public Integer getActid()
	{
		return actid;
	}
	public void setActid(Integer actid)
	{
		this.actid = actid;
	}
	public Groups getGroups()
	{
		return groups;
	}
	public void setGroups(Groups groups)
	{
		this.groups = groups;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	@Override
	public String toString()
	{
		return "Action [actid=" + actid + ", groups=" + groups + ", title=" + title + ", url=" + url + "]";
	}
	
	
}