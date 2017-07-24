package org.lyk.main;

import java.util.Date;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.vo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Hello
{
	public static void main(String[] args)
	{
		News news = new News();
		news.setTitle("热热热");
		news.setContent("真的很热");
		news.setPubdate(new Date());
		
		MyBatisSqlSessionFactory myBatisSqlSessionFactory = new MyBatisSqlSessionFactory();
//		SqlSessionFactory sqlSessionFactory = myBatisSqlSessionFactory.getSqlSessionFactory();
		
		System.out.println(myBatisSqlSessionFactory.getNews());
		System.out.println("//Main done");
	}
}
