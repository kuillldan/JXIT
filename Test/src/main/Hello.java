package main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import factory.ServiceFactory;
import service.IDeptService;
import service.impl.DeptServiceImpl;
import utils.BeanOperator;
import utils.StringUtils; 
import vo.Dept;
import vo.Employee;

interface IPrintable
{
	public void print();
}

class Worker implements IPrintable
{

	@Override
	public void print()
	{
		System.out.println("当前时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
	}
}

class WorkerProxy implements IPrintable
{
	IPrintable realObject = null;
	public WorkerProxy(IPrintable realObject)
	{
		super();
		this.realObject = realObject;
	}

	private void prework()
	{
		System.out.println("====前期工作 ====");
	}
	private void afterwork()
	{
		System.out.println("====后期工作 ====");
	}
	
	
	@Override
	public void print()
	{
		this.prework();
		this.realObject.print();
		this.afterwork();
	}
}

class WorkerDynamicProxy implements InvocationHandler
{
	private Object realObject = null;
	String msg = "代理类的message";
	public WorkerDynamicProxy(Object realObject)
	{
		super();
		this.realObject = realObject;
	}
	private void prework()
	{
		System.out.println("====前期工作 ====");
	}
	private void afterwork()
	{
		System.out.println("====后期工作 ====");
	}

	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		this.prework();
		Object retVal = method.invoke(this.realObject, args);
		this.afterwork();
		return retVal;
	}
}

class MyClassLoader extends ClassLoader
{
	public Class<?> loadFromByte(byte[] classInByte,String className)
	{
		return super.defineClass(className, classInByte, 0, classInByte.length);
	}
}


public class Hello
{
	public static void main(String[] args) throws Exception
	{ 
		URL url = new URL("http://192.168.1.103:8080/ClassServer/allClasses/Evil.class");
		URLConnection conn = url.openConnection();
		InputStream is = conn.getInputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while((len = is.read(buffer)) > -1)
		{
			bos.write(buffer, 0, len);
		}
		byte[] classesInByte = bos.toByteArray();
		Class<?> evilClass = new MyClassLoader().loadFromByte(classesInByte, "vo.Evil");
		System.out.println(evilClass.newInstance());
		is.close();
		
		
		
		System.out.println("//Main done~~~");
	}

	public static void printDeclaredMethods(String className) throws ClassNotFoundException
	{
		Class<?> bookClass = Class.forName(className);
		Method[] allMethods = bookClass.getDeclaredMethods();
		for (int i = 0; i < allMethods.length; i++)
		{
			Method method = allMethods[i];
			StringBuffer methodString = new StringBuffer();
			methodString.append(Modifier.toString(method.getModifiers())).append(" ");
			Class<?> returnType = method.getReturnType();
			methodString.append(returnType.getSimpleName()).append(" ");
			methodString.append(method.getName());
			methodString.append("(");

			Class<?>[] parameterTypes = method.getParameterTypes();
			for (int j = 0; j < parameterTypes.length; j++)
			{
				methodString.append(parameterTypes[j].getSimpleName()).append(" args").append(j);

				if (j < parameterTypes.length - 1)
				{
					methodString.append(", ");
				}
			}

			methodString.append(")");

			Class<?>[] allExceptionTypes = method.getExceptionTypes();
			if (allExceptionTypes != null && allExceptionTypes.length > 0)
			{
				methodString.append("throws ");
				for (int k = 0; k < allExceptionTypes.length; k++)
				{
					methodString.append(allExceptionTypes[k].getSimpleName());
					if (k < allExceptionTypes.length - 1)
					{
						methodString.append(", ");
					}
				}
			}

			System.out.println(methodString);
		}
	}

	public static void printConstructors(String className) throws ClassNotFoundException
	{
		Class<?> bookClass = Class.forName(className);
		Constructor<?>[] allConstructors = bookClass.getConstructors();
		for (int i = 0; i < allConstructors.length; i++)
		{
			Constructor<?> cons = allConstructors[i];
			StringBuffer conString = new StringBuffer();
			conString.append(Modifier.toString(cons.getModifiers())).append(" ");
			conString.append(cons.getName()).append("(");
			Class<?>[] allParameterTypes = cons.getParameterTypes();
			for (int j = 0; j < allParameterTypes.length; j++)
			{
				conString.append(allParameterTypes[j].getSimpleName()).append(" args").append(j);
				if (j < allParameterTypes.length - 1)
				{
					conString.append(", ");
				}
			}

			conString.append(")");

			Class<?>[] allExceptions = cons.getExceptionTypes();
			if (allExceptions != null && allExceptions.length > 0)
			{
				conString.append("throws ");
				for (int k = 0; k < allExceptions.length; k++)
				{
					conString.append(allExceptions[k].getSimpleName());
					if (k < allExceptions.length - 1)
					{
						conString.append(", ");
					}
				}
			}

			System.out.println(conString);
		}
	}
}