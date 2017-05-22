package vo;

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

}