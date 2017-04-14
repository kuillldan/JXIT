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
import org.lyk.vo.TestDemo;
 
import utils.StringUtils;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		TestDemo td = new TestDemo();

		String properties = "emp.dept.company.priceList";
		String[] arrayValues = {"10.6","2.0","31.2","42.2"};
		BeanOperate bo = new BeanOperate(td, properties, arrayValues);
		
		System.out.println(td);
		
		
		properties = "emp.name";
		String value = "刘文良";
		bo = new BeanOperate(td, properties, value);
		
		System.out.println(td);
		
		System.out.println("///Main done~~");
	}
}
