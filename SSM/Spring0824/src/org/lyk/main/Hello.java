package org.lyk.main;

  

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.lyk.dbc.MyBatisSessionFactory;
import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.lyk.vo.Member;
import org.lyk.vo.Student;
import org.lyk.vo.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

 

public class Hello
{
	private static final Logger logger = LoggerFactory.getLogger(Hello.class);
	private static final String MAPPING = "org.lyk.vo.mapping.MemberNS.";
	private static final ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	public static void main(String[] args) throws IOException
	{ 
		
	//	SqlSession sqlSession = MyBatisSessionFactory.getSession();
//		Student student = new Student();
//		student.setMid("21591928");
//		student.setName("远奎");
//		student.setAge(19);
//		student.setSchool("重庆大学");
//		student.setScore(99.5);
//		student.setFlag("学生");
//		Worker worker = new Worker();
//		worker.setMid("21591930");
//		worker.setName("加爵");
//		worker.setAge(35);
//		worker.setCompany("云南大学");
//		worker.setSalary(93.3);
//		
//		sqlSession.insert(MAPPING + "doCreateWorker",worker);
//		
		IDeptService deptServiceImpl = ctx.getBean("deptServiceImpl",IDeptService.class);
		Dept dept = new Dept();
		dept.setDeptno(55);
		dept.setDname("SSIT");
		deptServiceImpl.insert(dept);
		logger.info("//Main Done");
	}

}
