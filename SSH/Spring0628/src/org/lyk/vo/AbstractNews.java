package org.lyk.vo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AbstractNews entity provides the base persistence definition of the News
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractNews implements java.io.Serializable
{

	// Fields

	private Integer nid;
	private String title;
	private Date pubdate;
	private String content;

	// Constructors

	/** default constructor */
	public AbstractNews()
	{
	}

	/** full constructor */
	public AbstractNews(String title, Date pubdate, String content)
	{
		this.title = title;
		this.pubdate = pubdate;
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

	@Column(name = "title", length = 50)
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

	@Column(name = "content", length = 65535)
	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Override
	public String toString()
	{
		return "AbstractNews [nid=" + nid + ", title=" + title + ", pubdate="
				+ pubdate + ", content=" + content + "]";
	}
	
}