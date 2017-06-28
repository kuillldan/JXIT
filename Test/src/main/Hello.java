package main;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

import utils.StringEncoder;

class News
{
	private String title;
	private String content;
	private Date pubdate;

	public News(String title, String content, Date pubdate)
	{
		super();
		this.title = title;
		this.content = content;
		this.pubdate = pubdate;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getPubdate()
	{
		return pubdate;
	}
	public void setPubdate(Date pubdate)
	{
		this.pubdate = pubdate;
	}
	@Override
	public String toString()
	{
		return "News [title=" + title + ", content=" + content + ", pubdate="
				+ pubdate + "]";
	} 
}


class NewsHelper
{
	private static ThreadLocal<News> threadLocal = new ThreadLocal<News>();
	public static void setNews(News news)
	{
		threadLocal.set(news);
	}
	
	public static News getNews()
	{
		return threadLocal.get();
	}
}

class NewsPrinter
{
	
	public static void print()
	{
		System.out.println(NewsHelper.getNews());
	}
}

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] encodedMsg = md5.digest("".getBytes("UTF8"));
		System.out.println(encodedMsg.length);
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < encodedMsg.length;i++)
		{
			int charInt = encodedMsg[i];
			if(charInt < 0 )
			{
				charInt += 256;
			}
			if(charInt < 16)
			{
				sb.append("0");
			}
			sb.append(Integer.toHexString(charInt));
		}
		System.out.println(sb.toString());
	}
}
