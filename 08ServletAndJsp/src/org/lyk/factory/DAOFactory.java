package org.lyk.factory;

import java.sql.Connection;

import org.lyk.dao.IAdminDAO;
import org.lyk.dao.IDeptDAO;
import org.lyk.dao.IEmpDAO;
import org.lyk.dao.impl.AdminDAOImpl;
import org.lyk.dao.impl.DeptDAOImpl; 
import org.lyk.dao.impl.EmpDAOImpl;

public class DAOFactory
{
	public static IDeptDAO getIDeptDAOInstance(Connection conn)
	{
		return new DeptDAOImpl(conn);
	}
	
	public static IEmpDAO getIEmpDAOInstance(Connection conn)
	{
		return new EmpDAOImpl(conn);
	}
	
	public static IAdminDAO getIAdminDAOInstance(Connection conn)
	{
		return new AdminDAOImpl(conn);
	}
}
