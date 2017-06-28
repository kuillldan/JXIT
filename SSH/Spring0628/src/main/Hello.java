package main;

import org.lyk.service.IAdminService;
import org.lyk.service.impl.AdminServiceImpl;
import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		IAdminService adminService = springContext.getBean("adminServiceImpl",AdminServiceImpl.class);
		adminService.login();
		System.out.println("//Main done~~~");
	}
}