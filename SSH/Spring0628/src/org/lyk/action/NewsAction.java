package org.lyk.action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.lyk.service.INewsService;
import org.lyk.vo.News;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Repository
@ParentPackage("root")
@Namespace("/pages/news")
@Action(value = "NewsAction")
public class NewsAction extends ActionSupport
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	public NewsAction()
	{
		System.out.println("*****创建Servlet NewsAction");
//		this.request = ServletActionContext.getRequest();
//		this.response = ServletActionContext.getResponse();
	}

	@Resource
	private INewsService newsService;

	private News news = new News();

	public News getNews()
	{
		return news;
	}

	public void insert()
	{
		try
		{
			if (this.newsService.insert(this.news))
			{
				System.out.println("增加成功");
			} else
			{
				System.out.println("增加失败");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void update()
	{
		try
		{
			if (this.newsService.update(this.news))
			{
				System.out.println("更新成功");
			} else
			{
				System.out.println("更新失败");
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete()
	{
		String idsInString = ServletActionContext.getRequest().getParameter(
				"ids");
		String[] idsInArray = idsInString.split("\\|");
		Set<Integer> ids = new HashSet<Integer>();
		for (String idInString : idsInArray)
		{
			ids.add(Integer.parseInt(idInString));
		}
		try
		{
			if (this.newsService.delete(ids))
			{
				System.out.println("删除成功");
			} else
			{
				System.out.println("删除失败");
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void get()
	{
		Integer nid = this.news.getNid();
		try
		{
			News news = this.newsService.get(nid);
			System.out.println("结果:" + news);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void list()
	{
		try
		{
			List<News> allNews = this.newsService.list();
			for(News news : allNews)
			{
				System.out.println(news);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void listSplit()
	{
		this.request = ServletActionContext.getRequest();
		String column = this.request.getParameter("column");
		String keyWord = this.request.getParameter("keyWord");
		Integer currentPage = Integer.parseInt(this.request.getParameter("currentPage"));
		Integer lineSize = Integer.parseInt(this.request.getParameter("lineSize"));
		try
		{
			Map<String, Object> map = this.newsService.list("title", keyWord, currentPage, lineSize);
			List<News> allNews = (List<News>)map.get("allNews");
			Integer allNewsCount = (Integer)map.get("allNewsCount");
			System.out.println("Total News Count:" + allNewsCount);
			for(News news : allNews)
			{
				System.out.println(news);
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
