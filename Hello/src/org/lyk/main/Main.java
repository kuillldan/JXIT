package org.lyk.main;

import java.io.FileInputStream;
import java.io.InputStream;
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
		
		Double initial = 1000000.0;
		Double times = 0.04;
		Integer years = 10;
		Double total = initial;
		for(int i = 1; i <= years; i++)
		{
			total += total*times;
			System.out.println("year " + i + " :" + total/10000);
		}
		
		System.out.println("\r\n---------------------");
		System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));;
		System.out.println("///Main done~~");
	} 
}
