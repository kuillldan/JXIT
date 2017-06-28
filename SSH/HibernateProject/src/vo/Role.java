package vo;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable
{

	// Fields

	private Integer rid;
	private String title;
	private String note;
	private Set groupses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role()
	{
	}

	/** full constructor */
	public Role(String title, String note, Set groupses)
	{
		this.title = title;
		this.note = note;
		this.groupses = groupses;
	}

	// Property accessors

	public Integer getRid()
	{
		return this.rid;
	}

	public void setRid(Integer rid)
	{
		this.rid = rid;
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

	public Set getGroupses()
	{
		return this.groupses;
	}

	public void setGroupses(Set groupses)
	{
		this.groupses = groupses;
	}

	@Override
	public String toString()
	{
		return "Role [rid=" + rid + ", title=" + title + ", note=" + note + this.groupses.size() + "]";
	}

	
	
}