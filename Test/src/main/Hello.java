package main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import dbc.DatabaseConnection;

enum Color
{
	RED(1),BLUE(2),YELLOW(3),BLACK(4);
	
	private Integer value;
	private Color(Integer value)
	{
		this.value = value;
	}
	
	public Integer getValue()
	{
		return value;
	}
}

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		func("aa","bb","cc");
		System.out.println("//main done");
	}
	
	public static void func(String ... strs)
	{
		System.out.println(strs.length);
	}
}
