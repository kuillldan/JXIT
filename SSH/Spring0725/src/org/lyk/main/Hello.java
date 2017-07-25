package org.lyk.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.lyk.service.IDeptService;
import org.lyk.service.IMessageService;
import org.lyk.service.impl.DeptServiceImpl;
import org.lyk.service.impl.MessageServiceImpl;
import org.lyk.util.ResourceUtil;
import org.lyk.vo.Dept;
import org.lyk.vo.Emp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class Hello
{
	private static final String CONFIG_FILE = "applicationContext.xml";
	private static ApplicationContext ctx = null;
	static
	{
		ctx = new ClassPathXmlApplicationContext(CONFIG_FILE);
	}

	public static void main(String[] args) throws IOException
	{
		
		IMessageService messageServiceImpl = ctx.getBean("messageServiceImpl",IMessageService.class);
		System.out.println(messageServiceImpl.remove("22"));
		System.out.println("//Main done");
	}
}
