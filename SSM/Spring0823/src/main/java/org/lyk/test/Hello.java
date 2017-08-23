package org.lyk.test;

import org.lyk.service.IMessageService;
import org.lyk.vo.Dept;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Hello
{

	public static void main(String[] args)
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Dept dept = ctx.getBean("dept",Dept.class);
		System.out.println(dept);
	}

}
