package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import vo.Dept;
import vo.Emp;
 
public class Main
{
	public static void main(String[] args)
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("springConfig.xml");
		Dept dept = ctx.getBean("dept",Dept.class);
		System.out.println(dept);
		System.out.println("//Main done~~~");
	}
}
