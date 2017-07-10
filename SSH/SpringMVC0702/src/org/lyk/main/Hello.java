package org.lyk.main;

import java.io.IOException;
import java.util.Scanner;
 


import org.lyk.service.IMessageService;
import org.lyk.utils.ResourceUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

//import org.springframework.web.servlet.DispatcherServlet;

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		IMessageService messageImpl = ctx.getBean("messageImpl",IMessageService.class);
		System.out.println(messageImpl.remove("998"));
		System.out.println("===============");
	}
}
