package lyk.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news", catalog = "hedb")
public class News implements java.io.Serializable
{

	// Fields

	private Integer nid;
	private String title;
	private String content;
	
	
	

	public News(Integer nid, String title, String content)
	{
		super();
		this.nid = nid;
		this.title = title;
		this.content = content;
	}

	// Constructors

	/** default constructor */
	public News()
	{
	}

	/** minimal constructor */
	public News(String title)
	{
		this.title = title;
	}

	/** full constructor */
	public News(String title, String content)
	{
		this.title = title;
		this.content = content;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "nid", unique = true, nullable = false)
	public Integer getNid()
	{
		return this.nid;
	}

	public void setNid(Integer nid)
	{
		this.nid = nid;
	}

	@Column(name = "title", nullable = false, length = 50)
	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Column(name = "CONTENT", length = 100)
	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((nid == null) ? 0 : nid.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (content == null)
		{
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (nid == null)
		{
			if (other.nid != null)
				return false;
		} else if (!nid.equals(other.nid))
			return false;
		if (title == null)
		{
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "News [nid=" + nid + ", title=" + title + ", content=" + content
				+ "]";
	} 

}