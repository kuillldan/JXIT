package org.lyk.main;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.*; 
import java.text.*;
import java.util.*;
import java.util.Date;

import org.lyk.annotations.ClassInfo; 
import org.lyk.vo.InfoAccessible;
import org.lyk.vo.LYKClassLoader;

import utils.StringUtils;


@ClassInfo(className="org.lyk.vo.Student")
public class Main
{
	public static void main(String[] args) throws Exception
	{
		InfoAccessible ia = getInfoAccessible();
		ia.showInfo();
		System.out.println("///Main done~~");
	}
	
	
	public static InfoAccessible getInfoAccessible() throws Exception
	{
		 InfoAccessible ia = null;
		 ClassInfo ci = (ClassInfo)Main.class.getAnnotation(ClassInfo.class);
		 ia = (InfoAccessible)Class.forName(ci.className()).newInstance();
		 return ia;
	}
}
