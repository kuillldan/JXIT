package org.lyk.dbc;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisSessionFactory
{
	private static final Logger logger = LoggerFactory.getLogger(MyBatisSessionFactory.class);
	private static final String CONFIG_FILE = "mybatis.cfg.xml";
	
	private static Reader reader;
	private static SqlSessionFactory sessionFactory;
	private static ThreadLocal<SqlSession> threadLocal ;
	
	static
	{
		try
		{
			threadLocal = new ThreadLocal<SqlSession>();
			reader = Resources.getResourceAsReader(CONFIG_FILE);
			//此处可否用其它方式构建？
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e)
		{
			logger.error("构建SqlSessionFactory发生异常");
			logger.error(e.getMessage(), e);
		}
	}
	
	public static SqlSessionFactory getSessionFactory()
	{
		if(sessionFactory == null)
		{
			rebuildSessionFactory();
		}
		return sessionFactory;
	}
	
	public static SqlSession getSession()
	{
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession == null)
		{
			if(sessionFactory == null)
			{
				rebuildSessionFactory();
			}
			
			sqlSession = sessionFactory.openSession();
			threadLocal.set(sqlSession);
		}
		return sqlSession;
	}
	
	public static void closeSession()
	{
		SqlSession sqlSession = threadLocal.get();
		threadLocal.set(null);
		if(sqlSession != null)
		{
			sqlSession.close();
		}
	}
	
	private static void rebuildSessionFactory()
	{
		try
		{
			reader = Resources.getResourceAsReader(CONFIG_FILE);
			//此处可否用其它方式构建？
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e)
		{
			logger.error("重建SqlSessionFactory发生异常");
			logger.error(e.getMessage(), e);
		}
	}
}
