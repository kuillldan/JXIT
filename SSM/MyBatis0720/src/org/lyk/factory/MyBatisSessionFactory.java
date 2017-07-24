package org.lyk.factory;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSessionFactory
{
	private static final String CONFIG_FILE = "mybatis.cfg.xml";
	private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

	private static Reader reader = null;
	private static SqlSessionFactory sqlSessionFactory = null; 

	static
	{
		try
		{
			reader = Resources.getResourceAsReader(CONFIG_FILE);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); 
			threadLocal.set(sqlSessionFactory.openSession());

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory()
	{
		return sqlSessionFactory;
	}
	public static SqlSession getSqlSession()
	{
		return threadLocal.get();
	}
	public static void closeSqlSession()
	{
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession != null)
			sqlSession.close();
	}
}
