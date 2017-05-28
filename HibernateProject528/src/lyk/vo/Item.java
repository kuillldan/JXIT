package lyk.vo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Item entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "item", catalog = "hedb")
public class Item implements java.io.Serializable
{

	// Fields

	private Integer iid;
	private String title;
	private Set<Subitem> subitems = new HashSet<Subitem>(0);

	// Constructors

	/** default constructor */
	public Item()
	{
	}

	/** full constructor */
	public Item(String title, Set<Subitem> subitems)
	{
		this.title = title;
		this.subitems = subitems;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "iid", unique = true, nullable = false)
	public Integer getIid()
	{
		return this.iid;
	}

	public void setIid(Integer iid)
	{
		this.iid = iid;
	}

	@Column(name = "title", length = 50)
	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
	public Set<Subitem> getSubitems()
	{
		return this.subitems;
	}

	public void setSubitems(Set<Subitem> subitems)
	{
		this.subitems = subitems;
	}

	@Override
	public String toString()
	{
		return "Item [iid=" + iid + ", title=" + title + ", subitems="
				+ subitems + "]";
	}
}