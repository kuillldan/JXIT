package org.lyk.main;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.vo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class Hello
{
	public static void main(String[] args)
	{
		News news = new News();
		news.setTitle("热热热");
		news.setContent("真的很热");
		news.setPubdate(new Date());

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory = ctx.getBean("sqlSessionFactory", SqlSessionFactory.class);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		System.out.println(sqlSession.insert("org.lyk.vo.mapping.NewsNS.doCreate",news));
		System.out.println(sqlSession.selectOne("org.lyk.vo.mapping.NewsNS.findById",1));
		System.out.println("//Main done");
	}
}
