package lyk.action;

import lyk.vo.News;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
@ParentPackage("root")
@Action("NewsAction")
@Namespace("/pages/news")
@Results({@Result(name="input",location="/pages/news/news_input.jsp")})
public class NewsAction extends ActionSupport
{
	private News news =  new News();
	public News getNews()
	{
		return news;
	}
	
	public void insert()
	{
		System.out.println("******insert");
		System.out.println(this.news);
	}
	
	@Override
	public void validate()
	{
		System.out.println("*******validate");
		//super.addFieldError("abc", "就是有一点错误");
	}
}
