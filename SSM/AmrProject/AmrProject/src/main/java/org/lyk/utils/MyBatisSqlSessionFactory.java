package org.lyk.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisSqlSessionFactory
{
	private static final Logger logger = LoggerFactory.getLogger(CommonConstant.LOGFILE);
	private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

	private static final String CONFIG_FILE = "mybatis.cfg.xml";
	private static InputStream is;
	private static SqlSessionFactory sqlSessionFactory;

	static
	{
		rebuildSessionFactory();
	}

	private static void rebuildSessionFactory()
	{
		try
		{
			is = Resources.getResourceAsStream(CONFIG_FILE);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			is.close();
		} catch (IOException e)
		{
			logger.error("构建MyBatisSessionFactory失败!");
			logger.error(e.getMessage(), e);
		}
	}

	public static SqlSessionFactory getFactory()
	{
		if (sqlSessionFactory == null)
		{
			rebuildSessionFactory();
		}
		return sqlSessionFactory;
	}

	public static SqlSession getSession()
	{
		if (threadLocal.get() == null)
		{
			if (sqlSessionFactory == null)
				rebuildSessionFactory();

			SqlSession sqlSession = sqlSessionFactory.openSession();
			threadLocal.set(sqlSession);
		}

		return threadLocal.get();
	}

	public static void close()
	{
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession != null)
		{
			threadLocal.remove();
			sqlSession.close();
		}
	}
}
