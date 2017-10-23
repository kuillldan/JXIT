package org.lyk;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.lyk.entities.HttpClient;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class StartSpringBoot extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		// TODO Auto-generated method stub
		return builder.sources(StartSpringBoot.class);
	}

	public static void main(String[] args) throws Exception
	{
		saveAllFile(50);
		SpringApplication.run(StartSpringBoot.class, args);
	}
	
	public static void saveAllFile(Integer max) throws Exception
	{
		System.setProperty("proxyPort", "8080");
		System.setProperty("proxyHost", "web-proxy.jp.hpecorp.net");
		System.setProperty("proxySet", "true");

		for (int i = 1; i <= max; i++)
		{
			String sendRecvGet = HttpClient.sendGet("http://www.t66y.com/thread0806.php", "fid=22&search=&page=" + i);
			OutputStream os = new FileOutputStream("C:\\D\\JXIT\\temp\\caoliu\\" + i + ".txt");
			os.write(sendRecvGet.getBytes());
			os.close();
			System.out.println("成功保存(" + i + ")个文件. 源:http://www.t66y.com/thread0806.php?fid=22&search=&page=" + i);
		}
	}
}
