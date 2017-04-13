package org.lyk.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import org.lyk.vo.LYKClassLoader;

import utils.StringUtils;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		//用户自定义的类用扩展加载器
		//系统类
		
		
		LYKClassLoader lykClassLoader = new LYKClassLoader();
		Class dateClass = lykClassLoader.getMyClass("org.lyk.vo.Dept");
		System.out.println(dateClass.getName());
		
		
		
		
		System.out.println("///Main done~~");
	}
}
