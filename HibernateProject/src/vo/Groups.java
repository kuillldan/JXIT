package vo;

import java.util.HashSet;
import java.util.Set;

/**
 * Groups entity. @author MyEclipse Persistence Tools
 */

public class Groups implements java.io.Serializable
{

	// Fields

	private Integer gid;
	private String title;
	private String note;
	private Set roles = new HashSet(0);

	// Constructors

	/** default constructor */
	public Groups()
	{
	}

	/** full constructor */
	public Groups(String title, String note, Set roles)
	{
		this.title = title;
		this.note = note;
		this.roles = roles;
	}

	// Property accessors

	public Integer getGid()
	{
		return this.gid;
	}

	public void setGid(Integer gid)
	{
		this.gid = gid;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getNote()
	{
		return this.note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public Set getRoles()
	{
		return this.roles;
	}

	public void setRoles(Set roles)
	{
		this.roles = roles;
	}

	@Override
	public String toString()
	{
		return "Groups [gid=" + gid + ", title=" + title + ", note=" + note + ""+this.roles.size()+"]";
	}

	
}