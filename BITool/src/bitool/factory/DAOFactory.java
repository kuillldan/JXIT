package bitool.factory;

import java.sql.Connection;

import bitool.dao.IAccountManagementDAO;
import bitool.dao.impl.AccountManagementDAOImpl;

public class DAOFactory
{
	public static IAccountManagementDAO getAccountManagementDAOInstance(Connection conn)
	{
		return new AccountManagementDAOImpl(conn);
	}
}
