package factories;

import java.sql.Connection;

import DAO.IAdminDAO;
import DAOImpl.AdminDAOImpl;

public class DAOFactory
{
	public static IAdminDAO getIAdminDAOInstance(Connection conn)
	{
		return new AdminDAOImpl(conn);
	}
}
