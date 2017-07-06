package bitool.factory;

import java.sql.Connection;

import bitool.dao.IAccountManagementDAO;
import bitool.dao.IOpenOffManagement;
import bitool.dao.impl.AccountManagementDAOImpl;
import bitool.dao.impl.OpenOffManagementImpl;

public class DAOFactory
{
	public static IAccountManagementDAO getAccountManagementDAOInstance(Connection conn)
	{
		return new AccountManagementDAOImpl(conn);
	}
	
	public static IOpenOffManagement getOpenOffManagementDAOInstance(Connection conn)
	{
		return new OpenOffManagementImpl(conn);
	}
}
