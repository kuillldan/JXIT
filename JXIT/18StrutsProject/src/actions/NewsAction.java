package actions;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import utils.StringUtils;
import vo.News;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class NewsAction extends ActionSupport
{
	
	private String insertRule = "news.nid:int|news.title:String|news.pubdate:date|news.content:String";
	private String updateRule = "news.nid:int|news.title:String";
	private String otherInfo;
	public void setOtherInfo(String otherInfo)
	{
		this.otherInfo = otherInfo;
	}
	
	private News news = new News();  
	public News getNews()
	{
		return news;
	}
	
	
	public String insert()
	{
		System.out.println( "newsAction.insert"+this.news + " 其它信息:" + this.otherInfo);
		return "good";
	} 
}