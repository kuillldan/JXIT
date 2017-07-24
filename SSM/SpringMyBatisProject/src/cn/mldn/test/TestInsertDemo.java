package cn.mldn.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mldn.service.INewsService;
import cn.mldn.vo.News;

public class TestInsertDemo {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		INewsService service = ctx.getBean("newsServiceImpl",
				INewsService.class);
		News vo = new News() ;
		vo.setTitle("MyBatis很重要");
		vo.setContent("开发中都用它，你说重要不重要！");
		vo.setPubdate(new Date());
		System.out.println(service.insert(vo));
	}
}
