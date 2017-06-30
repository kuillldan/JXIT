package org.lyk.main;
 
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.lyk.dao.INewsDAO; 
import org.lyk.service.INewsService;
import org.lyk.vo.News; 
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Service
public class Hello
{
	public static void main(String[] args) throws Exception
	{    
		ApplicationContext springContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		INewsService newsService = springContext.getBean("newsServiceImpl",INewsService.class);
		System.out.println(newsService.list("title","b",1,3).get("allNewsCount"));
		List<News> allNews = (List<News>)(newsService.list("title","b",1,3).get("allNews"));
		System.out.println(allNews.size());
		
		System.out.println("//Main done~~~");
	}

	public static void showInfo()
	{
		System.out.println("测试函数");
	}
}