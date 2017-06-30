package org.lyk.vo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news", catalog = "springdb")
public class News extends AbstractNews implements java.io.Serializable
{

	// Constructors

	/** default constructor */
	public News()
	{
	}

	/** full constructor */
	public News(String title, Date pubdate, String content)
	{
		super(title, pubdate, content);
	}
}
