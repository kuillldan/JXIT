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
		String filePathANSI = "C:\\D\\Programs\\apache-jmeter-2.13\\bin\\sheldon\\data\\dataANSI.txt";
		String filePathUTF8 = "C:\\D\\Programs\\apache-jmeter-2.13\\bin\\sheldon\\data\\dataUTF8.txt";
		
		InputStream isANSI = new FileInputStream(filePathANSI);
		InputStream isUTF8 = new FileInputStream(filePathUTF8);
		
		int buf = -1;
		while((buf=isANSI.read()) != -1)
		{
			System.out.print((char)buf+"|");
		}
		buf = -1;
		
		System.out.println("\r\n---------------------");
		byte[] buffer = new byte[4];
		while((buf=isUTF8.read(buffer, 0, 4)) != -1)
		{
			System.out.print(new String(buffer));
		}
		
		isANSI.close();
		isUTF8.close();
		System.out.println("\r\n---------------------");
		System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));;
		System.out.println("///Main done~~");
	} 
}
