package org.lyk.caoliu.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Post implements Serializable , Comparable<Post>
{
	private String title;
	private String href;
	private Integer commentCount;
	private String author;
	
	

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getHref()
	{
		return href;
	}

	public void setHref(String href)
	{
		this.href = href;
	}

	public Integer getCommentCount()
	{
		return commentCount;
	}

	public void setCommentCount(Integer commentCount)
	{
		this.commentCount = commentCount;
	}

	

	@Override
	public String toString()
	{
		return "Post [title=" + title + ", href=" + href + ", commentCount=" + commentCount + ", author=" + author
				+ "]";
	}

	@Override
	public int compareTo(Post o)
	{
		return o.commentCount.compareTo(this.commentCount);
	}
}
