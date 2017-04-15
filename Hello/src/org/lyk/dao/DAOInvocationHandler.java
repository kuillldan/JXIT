package org.lyk.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DAOInvocationHandler implements InvocationHandler
{
	private Object realObject;
	
	
	public DAOInvocationHandler(Object realObject)
	{
		super();
		this.realObject = realObject;
	}

	private void prepare()
	{
		System.out.println("====prepareing connection ====");
	}
	
	private void deStruct()
	{
		System.out.println("====closing connetionc =====");
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable
	{
		if(method.getName().startsWith("do"))
		{
			this.prepare();
			Object obj = method.invoke(this.realObject, args);
			this.deStruct();
			return obj;
		}
		else
		{
			return method.invoke(this.realObject, args);
		}
	}
}