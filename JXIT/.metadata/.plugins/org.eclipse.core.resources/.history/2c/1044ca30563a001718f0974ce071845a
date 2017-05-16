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
	private News news = new News();
	public News getNews()
	{
		return news;
	}
	
	public String insert()
	{
		System.out.println( "newsAction.insert"+this.news);
		
		return "good";
	}
	
//	@Override
//	public void validate()
//	{
//		if(this.news.getNid() == null)
////		{
////			super.addFieldError("news.nid", super.getText("data.null",new String[]{"新闻编号"}));
////		}
////		
////		if(StringUtils.isEmpty(this.news.getTitle()))
////		{
////			super.addFieldError("news.title", super.getText("data.null",new String[]{"新闻标题"}));
////		}
////		
////		if(StringUtils.isEmpty(this.news.getContent()))
////		{
////			super.addFieldError("news.content", super.getText("data.null",new String[]{"新闻内容"}));
////		}
////		
////		if(this.news.getPubdate() == null)
////		{
////			super.addFieldError("news.pubdate", super.getText("data.null",new String[]{"新闻发布日期"}));
////		}
//		
//		System.out.println("==================================================");
//		Map<String, List<String>> errors = super.getFieldErrors();
//		Set<Map.Entry<String, List<String>>> entrySet = errors.entrySet();
//		for(Map.Entry<String, List<String>> errorEntry : entrySet)
//		{
//			System.out.println(errorEntry.getKey() + " --> " + errorEntry.getValue());
//		}
//		System.out.println("==================================================");
//	}
}
