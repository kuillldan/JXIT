package org.lyk.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.lyk.utils.CommonConstant;
import org.lyk.utils.DateHelper;
import org.lyk.utils.ListHelper;
import org.lyk.utils.MyBatisSqlSessionFactory;
import org.lyk.vo.Member;
import org.lyk.vo.MemberDetails;
import org.lyk.vo.MemberLogin;
import org.lyk.vo.News;
import org.lyk.vo.Student;
import org.lyk.vo.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FackeRequest implements Runnable
{
	private static final String MAPPING_PREFIX = "org.lyk.vo.mapping.NewsNS.";
	private static final Logger logger = LoggerFactory.getLogger(CommonConstant.LOGFILE);
	public static Integer searchCount = 0;

	@Override
	public void run()
	{
		try
		{
			SqlSession sqlSession = MyBatisSqlSessionFactory.getSession();
			synchronized (Object.class)
			{
				searchCount++;

			}
			News news = sqlSession.selectOne(MAPPING_PREFIX + "findById", 2);
			sqlSession.commit();
			logger.info(news.toString());

			logger.info("****************************************");
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}
}

public class TestMyBatis
{
	private static final Logger logger = LoggerFactory.getLogger("logfile");
	private static final String MAPPING_PREFIX = "org.lyk.vo.mapping.MemberNS.";
	private static final String MEMEBER_LOGIN_MAPPING_PREFIX = "org.lyk.vo.mapping.MemberLoginNS.";
	private static final String MEMEBER_DETAILS_MAPPING_PREFIX = "org.lyk.vo.mapping.MemberDetailsNS.";

	public static void main(String[] args) throws Exception
	{
		SqlSession session = MyBatisSqlSessionFactory.getSession();
		MemberLogin ml = session.selectOne(MEMEBER_LOGIN_MAPPING_PREFIX + "findByMid", "21591923");		 
		System.out.println(ml.getMemberDetails().getMemberLogin());
		session.commit();

		MyBatisSqlSessionFactory.close();
	}
}
