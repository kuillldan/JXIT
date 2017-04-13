package org.lyk.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.*;
import java.text.*;
import java.util.*;

import utils.StringUtils;
 
public class Main
{

	public static void main(String[] args) throws Exception
	{   
		Class cls = Class.forName("org.lyk.vo.Dept");
		Object obj = cls.newInstance();
		
		String param = "dname:开发部|loc:北京";
		String[] values = param.split("\\|");
		for(String value : values)
		{
			String fieldName = value.split(":")[0];
			String fieldValue = value.split(":")[1];
			Method setter = cls.getMethod("set" + StringUtils.initCap( fieldName), String.class);
			setter.invoke(obj, fieldValue);
		}
		
		System.out.println(obj);
		
		
		
		System.out.println("///Main done~~")  ;
	}
}
    