package org.lyk.main;

import org.apache.log4j.Logger;
import org.lyk.service.IDeptService;
import org.lyk.service.IMessageService;
import org.lyk.service.impl.DeptServiceImpl;
import org.lyk.service.impl.MessageServiceImpl;
import org.lyk.vo.Dept;
import org.lyk.vo.Emp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Hello
{
	private static final String CONFIG_FILE = "applicationContext.xml";
	private static ApplicationContext ctx = null;
	static
	{
		ctx = new ClassPathXmlApplicationContext(CONFIG_FILE);
	}

	public static void main(String[] args)
	{
		Dept dept = new Dept();
		dept.setDeptno(2);
		dept.setDname("CMS");
		dept.setLoc("HUBEI");
		
		IDeptService deptServiceImpl = ctx.getBean("deptServiceImpl",DeptServiceImpl.class);
		deptServiceImpl.add(dept);
		
		System.out.println("//Main done");
	}
}
