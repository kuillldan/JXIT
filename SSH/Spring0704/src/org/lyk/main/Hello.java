package org.lyk.main;

import org.apache.log4j.Logger;
import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.lyk.vo.Emp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 

public class Hello
{
	public static void main(String[] args)
	{
		Dept dept = new Dept();
		dept.setDeptno(10);
		dept.setDname("开发部");
		dept.setLoc("北京");
		
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		applicationContext.getBean("deptServiceImpl",IDeptService.class).insert(dept);
	}
}
