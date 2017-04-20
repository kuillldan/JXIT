package org.lyk.factory;

import java.lang.reflect.Proxy;
import java.sql.Connection;

import org.lyk.dao.DAOInvocationHandler;
import org.lyk.dao.IDeptDAO;
import org.lyk.dao.IT01DAO;
import org.lyk.dao.impl.DeptDAOImpl;
import org.lyk.dao.impl.T01DAOImpl;

public class DAOFactory
{
	public static IDeptDAO getIDeptDAOInstance()
	{
		IDeptDAO realObject = new DeptDAOImpl();
		DAOInvocationHandler h = new DAOInvocationHandler(realObject);
		return (IDeptDAO)Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(), h);
	}
	
	public static IT01DAO getIt01daoInstance(Connection conn)
	{
		return new T01DAOImpl(conn);
	}
}
