package org.lyk.factory;

import java.lang.reflect.Proxy;

import org.lyk.dao.DAOInvocationHandler;
import org.lyk.dao.IDeptDAO;
import org.lyk.dao.impl.DeptDAOImpl;

public class DAOFactory
{
	public static IDeptDAO getIDeptDAOInstance()
	{
		DeptDAOImpl realObject = new DeptDAOImpl();
		DAOInvocationHandler h = new DAOInvocationHandler(realObject);
		return (IDeptDAO)Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(), h);
	}
}
