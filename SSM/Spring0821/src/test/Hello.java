package test;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.IDeptService;
import service.IMessageService;
import service.impl.DeptServiceImpl;
import util.ResourceUtil;
import vo.Dept;
import vo.Emp;

public class Hello
{
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static Logger logger = LoggerFactory.getLogger(Hello.class);
	public static void main(String[] args) throws IOException
	{
//		ResourceUtil resourceUtil = ctx.getBean("resourceUtil",ResourceUtil.class);
//		resourceUtil.print();
		Dept dept = new Dept();
		dept.setDeptno(10);
		dept.setDname("CMS");
		dept.setLoc("重庆");
//		IDeptService deptService = new DeptServiceImpl();
		IDeptService deptService = ctx.getBean("deptServiceImpl",IDeptService.class);
		deptService.insert(dept);
		logger.info("//Main Done~~~");
	}
}
