package cn.mldn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mldn.service.INewsService;

public class TestListDemo {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		INewsService service = ctx.getBean("newsServiceImpl",
				INewsService.class);
		System.out.println(service.list("title", "", 1, 3));
	}
}
