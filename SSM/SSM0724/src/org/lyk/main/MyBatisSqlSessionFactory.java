package org.lyk.main;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.vo.News;
import org.springframework.stereotype.Component;

@Component
public class MyBatisSqlSessionFactory
{
	@Resource
	private SqlSessionFactory sqlSessionFactory;
	
	@Resource
	private News news;
	
	public SqlSessionFactory getSqlSessionFactory()
	{
		return  this.sqlSessionFactory;
	}
	
	public News getNews()
	{
		return this.news;
	}
}
