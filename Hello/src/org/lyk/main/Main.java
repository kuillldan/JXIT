package org.lyk.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import org.lyk.annotations.ClassInfo;
import org.lyk.dao.IDeptDAO;
import org.lyk.dao.impl.DeptDAOImpl;
import org.lyk.factory.DAOFactory;
import org.lyk.vo.BeanOperate;
import org.lyk.vo.Dept;
import org.lyk.vo.InfoAccessible;
import org.lyk.vo.LYKClassLoader;
import org.lyk.vo.TestDemo;

import utils.StringUtils;

class A
{
	private String info ;

	@Override
	public String toString()
	{
		return "A [info=" + info + "]";
	}
	
}

 

public class Main
{
	public static void main(String[] args) throws Exception
	{
		A a = new A();
		
		Field infoField = a.getClass().getDeclaredField("info");
		infoField.setAccessible(true);
		infoField.set(a, "hello");
		System.out.println(a);
		
		System.out.println("///Main done~~");
	}

	public static <T> void show(T data)
	{
		System.out.println(data);
	}
}
