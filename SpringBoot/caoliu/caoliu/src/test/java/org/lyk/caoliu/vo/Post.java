package org.lyk.caoliu.vo;

import java.io.Serializable;

public class Post implements Serializable , Comparable<Post>
{
	private String title;
	private String href;
	private Integer commentCount;

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
		return "Post [title=" + title + ", href=" + href + ", commentCount=" + commentCount + "]";
	}

	@Override
	public int compareTo(Post o)
	{
		return this.commentCount.compareTo(o.commentCount);
	}
}
