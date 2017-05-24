package vo;

import java.util.HashSet;
import java.util.Set;

/**
 * Item entity. @author MyEclipse Persistence Tools
 */

public class Item implements java.io.Serializable
{

	// Fields

	private Integer iid;
	private String title;
	private Set subitems = new HashSet(0);

	// Constructors

	/** default constructor */
	public Item()
	{
	}

	/** full constructor */
	public Item(String title, Set subitems)
	{
		this.title = title;
		this.subitems = subitems;
	}

	// Property accessors

	public Integer getIid()
	{
		return this.iid;
	}

	public void setIid(Integer iid)
	{
		this.iid = iid;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Set getSubitems()
	{
		return this.subitems;
	}

	public void setSubitems(Set subitems)
	{
		this.subitems = subitems;
	}

}