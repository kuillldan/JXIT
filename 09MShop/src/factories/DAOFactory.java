package factories;

import java.sql.Connection;

import DAO.IAdminDAO;
import DAO.IDetailsDAO;
import DAO.IGoodsDAO;
import DAO.IItemDAO;
import DAO.IMemberDAO;
import DAO.IOrdersDAO;
import DAO.IShopCarDAO;
import DAOImpl.AdminDAOImpl;
import DAOImpl.DetailsDAOImpl;
import DAOImpl.GoodsDAOImpl;
import DAOImpl.ItemDAOImpl;
import DAOImpl.MemberDAOImpl;
import DAOImpl.OrdersDAOImpl;
import DAOImpl.ShopCarDAOImpl;

public class DAOFactory
{
	public static IMemberDAO getIMemberDAOInstance(Connection conn)
	{
		return new MemberDAOImpl(conn);
	}
	
	public static IAdminDAO getIAdminDAOInstance(Connection conn)
	{
		return new AdminDAOImpl(conn);
	}
	
	public static IItemDAO getIItemDAOInstance(Connection conn)
	{
		return new ItemDAOImpl(conn);
	}
	
	public static IGoodsDAO getIGoodsDAOInstance(Connection conn)
	{
		return new GoodsDAOImpl(conn);
	}
	
	public static IShopCarDAO getIShopCarDAOInstance(Connection conn)
	{
		return new ShopCarDAOImpl(conn);
	}
	
	public static IOrdersDAO getIOrdersDAOInstance(Connection conn)
	{
		return new OrdersDAOImpl(conn);
	}
	public static IDetailsDAO getIDetailsDAOInstance(Connection conn)
	{
		return new DetailsDAOImpl(conn);
	}
}
