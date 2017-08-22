package org.lyk.main;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.vo.Dept;
import org.lyk.vo.Emp;
import org.lyk.vo.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class Hello
{
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static Logger logger = LoggerFactory.getLogger(Hello.class);
	
	public static void main(String[] args)
	{
		Emp beanObj = ctx.getBean("emp",Emp.class);
		logger.info(beanObj.toString());
		System.out.println("//Main done");
	}
}
