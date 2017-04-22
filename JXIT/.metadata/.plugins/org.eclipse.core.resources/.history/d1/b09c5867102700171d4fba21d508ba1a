package factories;

import java.sql.Connection;

import DAO.IActionDAO;
import DAO.IAdminDAO;
import DAO.IAdminlogsDAO;
import DAO.IGroupsDAO;
import DAOImpl.ActionDAOImpl;
import DAOImpl.AdminDAOImpl;
import DAOImpl.AdminlogsDAOImpl;
import DAOImpl.GroupsDAOImpl;

public class DAOFactory
{
	public static IAdminDAO getIAdminDAOInstance(Connection conn)
	{
		return new AdminDAOImpl(conn);
	}
	
	public static IActionDAO getIActionDAOInstance(Connection conn)
	{
		return new ActionDAOImpl(conn);
	}
	
	public static IAdminlogsDAO getIAdminlogsDAOInstance(Connection conn)
	{
		return new AdminlogsDAOImpl(conn);
	}
	
	public static IGroupsDAO getIGroupsDAOInstance(Connection conn)
	{
		return new GroupsDAOImpl(conn);
	}
}
