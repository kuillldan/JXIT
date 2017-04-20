
package  factory;

import dao.*;

import java.lang.reflect.Proxy;
import java.sql.Connection;

import dao.*;
import dao.impl.T01DAOImpl;

public class DAOFactory
{ 
	
	public static IT01DAO getIt01daoInstance(Connection conn)
	{
		return new T01DAOImpl(conn);
	}
}
