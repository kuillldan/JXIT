package org.lyk.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;
import org.lyk.service.INewsService;
import org.lyk.vo.News;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jca.context.SpringContextResourceAdapter;

public class NewsServiceTest
{
	public static ApplicationContext springContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	public static INewsService newsService = springContext.getBean("newsServiceImpl",INewsService.class);
	@Test
	public void testInsert()
	{
		News news = new News();
		news.setTitle("abc");
		news.setPubdate(new Date());
		news.setContent("aaabbbcccddd");
		INewsService newsService = springContext.getBean("newsServiceImpl",INewsService.class);
		try
		{
			TestCase.assertEquals(newsService.insert(news), true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate()
	{
		News news = new News();
		news.setNid(2);
		news.setTitle("重庆将不会出现持续高温天气");
		news.setPubdate(new Date());
		news.setContent("重庆永远不会出现大幅高温天气");
		INewsService newsService = springContext.getBean("newsServiceImpl",INewsService.class);
		try
		{
			TestCase.assertEquals(newsService.update(news), true);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() throws Exception
	{
		Set<Integer> ids = new HashSet<Integer>();
		ids.add(2);
		ids.add(3);
		ids.add(4);
		TestCase.assertEquals(newsService.delete(ids), true);
	}

	@Test
	public void testGet() throws Exception
	{
		TestCase.assertNotNull(newsService.get(2));
	}

	@Test
	public void testList() throws Exception
	{
		TestCase.assertEquals(newsService.list().size(), 3);
	}

	@Test
	public void testListStringStringIntegerInteger() throws Exception
	{
		System.out.println(newsService.list("title","不会",1,3));
		//TestCase.assertEquals(newsService.list("title","不会",1,3).size(), 1);
	}

}
