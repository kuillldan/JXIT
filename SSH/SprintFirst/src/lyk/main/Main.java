package lyk.main;

import java.sql.Connection;

import lyk.service.IMemberService;
import lyk.service.impl.MemberServiceImpl;
import lyk.vo.Dept;
import lyk.vo.Emp;
import lyk.vo.Member;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
import org.springframework.jdbc.datasource.*;

public class Main
{
	public static void main(String[] args) throws Exception
	{ 
		DriverManagerDataSource driverManager = new DriverManagerDataSource();
		driverManager.setDriverClassName("com.mysql.jdbc.Driver");
		driverManager.setUsername("root");
		driverManager.setPassword("admin");
		driverManager.setUrl("jdbc:mysql://localhost:3306/hedb");
		Connection conn = driverManager.getConnection();
		System.out.println(conn);
		conn.close();
		
		System.out.println("//Main done~~~");
	}
}
