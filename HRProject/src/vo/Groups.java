package vo;

import java.io.Serializable;
import java.util.List;

//权限组
@SuppressWarnings("serial")
public class Groups implements Serializable
{
	private Integer gid;
	private String title;
	private String note;
	private List<Action> allActions;
	
	
	
	public List<Action> getAllActions()
	{
		return allActions;
	}
	public void setAllActions(List<Action> allActions)
	{
		this.allActions = allActions;
	}
	public Integer getGid()
	{
		return gid;
	}
	public void setGid(Integer gid)
	{
		this.gid = gid;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note = note;
	}
	@Override
	public String toString()
	{
		return "Groups [gid=" + gid + ", title=" + title + ", note=" + note + ", allActions=" + allActions + "]";
	}
	
	
}
