package vo;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class Role implements Serializable
{
	private Integer rid;
	private String title;
	private String note;
	private List<Admin> allAdmins;
	private List<Groups> allGroups;
	public Integer getRid()
	{
		return rid;
	}
	public void setRid(Integer rid)
	{
		this.rid = rid;
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
	public List<Admin> getAllAdmins()
	{
		return allAdmins;
	}
	public void setAllAdmins(List<Admin> allAdmins)
	{
		this.allAdmins = allAdmins;
	}
	public List<Groups> getAllGroups()
	{
		return allGroups;
	}
	public void setAllGroups(List<Groups> allGroups)
	{
		this.allGroups = allGroups;
		
	}
	@Override
	public String toString()
	{
		return "Role [rid=" + rid + ", title=" + title + ", note=" + note + ", allAdmins=" + allAdmins + ", allGroups="
				+ allGroups + "]";
	}
	
	
	
}
