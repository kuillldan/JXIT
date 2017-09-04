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
	private static final Logger logger = LoggerFactory.getLogger("bi");
	private static final String MAPPING = "org.lyk.vo.mapping.MemberNS.";
	private static final ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	public static void main(String[] args) throws IOException
	{
		logger.info("ABC");
	}

}
