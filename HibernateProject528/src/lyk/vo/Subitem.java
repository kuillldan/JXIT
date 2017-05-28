package lyk.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Subitem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "subitem", catalog = "hedb")
public class Subitem implements java.io.Serializable
{

	// Fields

	private Integer sid;
	private Item item;
	private String title;

	// Constructors

	/** default constructor */
	public Subitem()
	{
	}

	/** full constructor */
	public Subitem(Item item, String title)
	{
		this.item = item;
		this.title = title;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "sid", unique = true, nullable = false)
	public Integer getSid()
	{
		return this.sid;
	}

	public void setSid(Integer sid)
	{
		this.sid = sid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iid")
	public Item getItem()
	{
		return this.item;
	}

	public void setItem(Item item)
	{
		this.item = item;
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

}