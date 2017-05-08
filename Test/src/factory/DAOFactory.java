package factory;

import java.sql.Connection;

import dao.IDeptDAO;
import dao.impl.DeptDAOImpl;

public class DAOFactory
{
	public static IDeptDAO getIDeptDAOInstance(Connection conn)
	{
		return new DeptDAOImpl(conn);
	}
}
