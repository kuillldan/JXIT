package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.IMessageService;
import vo.Dept;
import vo.Emp;

public class Hello
{
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static Logger logger = LoggerFactory.getLogger(Hello.class);
	public static void main(String[] args)
	{
		Emp emp = ctx.getBean("emp",Emp.class);
		logger.info(emp.toString());
	}
}
