package org.lyk.main;



import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.lyk.dao.IAdminDAO;
import org.lyk.demo.Apple;
import org.lyk.demo.IFruit;
import org.lyk.service.IAdminService;
import org.lyk.service.impl.AdminServiceImpl;
import org.lyk.util.ResourceBean;
import org.lyk.vo.Dept;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.DispatcherServlet;

public class Hello
{

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static void main(String[] args) throws Exception
	{
//		Resource resource = new ByteArrayResource("hello world\nhello shit".getBytes());
//		Resource resource = new FileSystemResource("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 8.0\\**\\web.xml");
//		Resource resource = new ClassPathResource("applicationContext.xml");
		ResourceBean rb = ctx.getBean("resource",ResourceBean.class);
		
		InputStream is = rb.getInputStream();
		Scanner scanner = new Scanner(is);
		scanner.useDelimiter("\n");
		while(scanner.hasNext())
		{
			System.out.println(scanner.next());
		}
		scanner.close();
		
		System.out.println("//Main done");
	}

}
