package main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import dbc.DatabaseConnection;
 
public class Hello
{
	public static void main(String[] args) throws Exception
	{
		DatabaseConnection dbc = new DatabaseConnection();
		System.out.println(dbc.getConnection());
		dbc.close();
		System.out.println("//main done");
	}
}
