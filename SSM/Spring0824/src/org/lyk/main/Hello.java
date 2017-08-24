package org.lyk.main;

  

import java.io.IOException;
import java.util.Scanner;

import org.lyk.vo.Dept;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
 

public class Hello
{
	private static final ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static void main(String[] args)
	{
//		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = ctx.getBean("resourceUtil",ResourceUtils.class).getResource();
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(resource.getInputStream());
			scanner.useDelimiter(System.lineSeparator());
			while(scanner.hasNext())
			{
				System.out.println(scanner.next());
			} 
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(scanner != null)
			{
				scanner.close();
			}
		}
	}

}
