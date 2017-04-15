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



class Person
{
	private String name;
	private Integer age;
	
	
	
	public Person(String name, Integer age)
	{
		super();
		this.name = name;
		this.age = age;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Integer getAge()
	{
		return age;
	}
	public void setAge(Integer age)
	{
		this.age = age;
	}
	@Override
	public String toString()
	{
		return "Person [name=" + name + ", age=" + age + "]";
	}
}

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Person p = new Person("sheldon", 12);
		show(p);
		System.out.println("///Main done~~");
	}
	
	public static <T> void show(T data)
	{
		System.out.println(data);
	}
}
