package factory;

import java.sql.Connection;

import dao.IDeptDAO;
import dao.IEmpDAO;
import dao.impl.DeptDAOImpl;
import dao.impl.EmpDAOImpl;

public class DAOFactory
{
	public static IDeptDAO getDeptDAOInstance(Connection conn)
	{
		return new DeptDAOImpl(conn);
	}
	
	public static IEmpDAO getEmpDAOInstance(Connection conn)
	{
		return new EmpDAOImpl(conn);
	}
}
