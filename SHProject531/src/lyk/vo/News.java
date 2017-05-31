package lyk.vo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news", catalog = "hedb")
public class News implements java.io.Serializable
{

	// Fields

	private Integer nid;
	private String content;
	private String title;
	private Date pubdate;

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
	public News(String content, String title, Date pubdate)
	{
		this.content = content;
		this.title = title;
		this.pubdate = pubdate;
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

	@Column(name = "CONTENT", length = 100)
	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "pubdate", length = 0)
	public Date getPubdate()
	{
		return this.pubdate;
	}

	public void setPubdate(Date pubdate)
	{
		this.pubdate = pubdate;
	}

}