package org.lyk.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.lyk.utils.DateHelper;
import org.lyk.utils.MyBatisSqlSessionFactory;
import org.lyk.vo.Member;
import org.lyk.vo.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMyBatis
{
	private static final Logger logger = LoggerFactory.getLogger("logfile");

	public static void main(String[] args) throws Exception
	{
		try
		{
			SqlSession session = MyBatisSqlSessionFactory.getSession();
			List<News> allNews = session.selectList("org.lyk.vo.mapping.NewsNS.findAll");
			System.out.println(allNews);
			session.commit();
			session.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}
}
