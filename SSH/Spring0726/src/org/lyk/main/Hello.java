package org.lyk.main;

import org.lyk.dao.IAdminDAO;
import org.lyk.demo.Apple;
import org.lyk.demo.IFruit;
import org.lyk.service.IAdminService;
import org.lyk.service.impl.AdminServiceImpl;
import org.lyk.vo.Dept;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Hello
{

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static void main(String[] args)
	{
		IAdminDAO adminService = ctx.getBean("adminDAOImpl", IAdminDAO.class);
		adminService.findLogin();
		System.out.println("//Main done");
	}

}
