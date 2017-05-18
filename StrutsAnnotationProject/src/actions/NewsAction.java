package actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import utils.StringUtils;
import vo.News;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings({ "serial", "unused" })
@ParentPackage(value = "root")
@Action(value = "NewsAction", results = { @Result(name = "showJSP", location = "/pages/news/show.jsp"),
		@Result(name = "insertJSP", location = "/pages/news/insert.jsp"),
		@Result(name = "input", location = "/pages/news/insert.jsp"),
		@Result(name = "insertValidationFailed", location = "/pages/news/insert.jsp"),
		@Result(name = "updateValidationFailed", location = "/pages/news/insert.jsp") })
@InterceptorRefs(value = {  @InterceptorRef("generalInterceptor") })
public class NewsAction extends ActionSupport
{
	private String insertRule = "news.nid:Integer|news.title:String|news.content:String|news.pubdate:Date";
	private String updateRule = "news.nid:Integer|news.title:String|news.content:String";

	private News news = new News();

	public News getNews()
	{
		return news;
	}

	public void setNews(News news)
	{
		this.news = news;
	}

	public String insert()
	{
		System.out.println("================");
		System.out.println(this.news);
		System.out.println("================");
		return "showJSP";
	}

	public void update()
	{
		System.out.println("++++++++++++++++update");
	}

	@Override
	public void validate()
	{
		if (this.news.getNid() == null)
		{
			super.addFieldError("news.nid", "【validate】新闻编号不能为空");
		}

		if (StringUtils.isEmpty(this.news.getTitle()))
		{
			super.addFieldError("news.title", "【validate】新闻标题不能为空");
		}

		if (StringUtils.isEmpty(this.news.getContent()))
		{
			super.addFieldError("news.content", "【validate】新闻内容不能为空");
		}

		if (this.news.getPubdate() == null)
		{
			super.addFieldError("news.pubdate", "【validate】新闻发布日期不能为空");
		}
	}
}
