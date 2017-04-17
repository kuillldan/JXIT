package factories;

import java.sql.Connection;
 
import DAO.IGoodsDAO;
import DAO.IItemDAO; 
import DAOImpl.GoodsDAOImpl;
import DAOImpl.ItemDAOImpl; 

public class DAOFactory
{
	
	
	public static IItemDAO getIItemDAOInstance(Connection conn)
	{
		return new ItemDAOImpl(conn);
	}
	
	public static IGoodsDAO getIGoodsDAOInstance(Connection conn)
	{
		return new GoodsDAOImpl(conn);
	}
}
