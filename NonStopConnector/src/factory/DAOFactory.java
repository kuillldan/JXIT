
package  factory;

import dao.*;

import java.lang.reflect.Proxy;
import java.sql.Connection;

import dao.*;
import dao.impl.TBL_01DAOImpl;
import dao.impl.TBL_03DAOImpl;
import dao.impl.TBL_04DAOImpl;

public class DAOFactory
{ 
	
	public static ITBL_01DAO getIt01daoInstance(Connection conn)
	{
		return new TBL_01DAOImpl(conn);
	}
	
	public static ITBL_03DAO getIt03daoInstance(Connection conn)
	{
		return new TBL_03DAOImpl(conn);
	}
	
	public static ITBL_04DAO getItbl_04daoInstance(Connection conn)
	{
		return new TBL_04DAOImpl(conn);
	}
}
