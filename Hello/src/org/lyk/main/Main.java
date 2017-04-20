package org.lyk.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;

import org.lyk.factory.ServiceFactory;
import org.lyk.vo.T01;

import dbc.DatabaseConnection;
 
 
public class Main
{
	public static void main(String[] args) throws Exception
	{
		List<T01> allT01s = ServiceFactory.getIt01ServiceInstance().list();
		for(T01 item : allT01s)
		{
			System.out.println(item);
		}
		
		System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));;
		System.out.println("///Main done~~");
	} 
}
