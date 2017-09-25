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
			synchronized(Object.class)
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

	public static void main(String[] args) throws Exception
	{
		SqlSession session = MyBatisSqlSessionFactory.getSession();
//		Student student = new Student();
//		student.setMid("3");
//		student.setAge(18);
//		student.setName("远奎");
//		student.setSchool("铜梁中学");
//		student.setScore(99.5);
//		
//		Worker worker = new Worker();
//		worker.setMid("4");
//		worker.setName("文良");
//		worker.setAge(30);
//		worker.setCompany("慧与");
//		worker.setSalary(9999.0);
//		
//		session.insert(MAPPING_PREFIX+"doCreateStudent", student);
//		session.insert(MAPPING_PREFIX+"doCreateWorker", worker);
//		session.commit();

		Student student = session.selectOne(MAPPING_PREFIX+"findStudentById",3);
		Worker worker = session.selectOne(MAPPING_PREFIX+"findWorkerById",4);
		System.out.println(student);
		System.out.println(worker);
		MyBatisSqlSessionFactory.close();
	}
}
