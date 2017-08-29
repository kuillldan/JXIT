package org.lyk.main;

  

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.lyk.vo.Dept;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
 

public class Hello
{
	private static final ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static void main(String[] args) throws IOException
	{
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource("classpath:mybatis.cfg.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource.getInputStream());
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Dept dept = new Dept();
		dept.setDname("外派组");
		dept.setLoc("北京");
		
		System.out.println(sqlSession.insert("org.lyk.vo.mapping.DeptNS.doCreate", dept));
		System.out.println(dept.getDeptno());
		sqlSession.commit();
		System.out.println(dept.getDeptno());
		sqlSession.close();
		
		System.out.println("//Main Done");
	}

}
