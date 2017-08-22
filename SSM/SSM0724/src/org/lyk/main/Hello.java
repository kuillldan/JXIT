package org.lyk.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.service.IDeptService;
import org.lyk.service.IMessageService;
import org.lyk.util.ResourceUtil;
import org.lyk.vo.Dept;
import org.lyk.vo.Emp; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

public class Hello
{
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static Logger logger = LoggerFactory.getLogger(Hello.class);
	
	public static void main(String[] args) throws IOException
	{
		IMessageService messageServiceImpl = ctx.getBean("messageServiceImpl",IMessageService.class);
		messageServiceImpl.insert();
		System.out.println("//Main done");
	}
}
