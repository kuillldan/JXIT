package service.front.impl;

import java.util.List;
import java.util.Map;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.front.IOrdersServiceFront;
import vo.Details;
import vo.Orders;

public class OrdersServiceFrontImpl implements IOrdersServiceFront
{
	DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean insert(Orders orders, List<Details> allDetails) throws Exception
	{
		try
		{ 
			boolean flag = true;
			flag = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).doCreateOrders(orders);
			if(flag == false)
				return false;
			
			Integer oid = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findLastInsertId();
			orders.setOid(oid);
			for(Details details : allDetails)
			{
				details.setOrder(orders);
			}
			
			flag = DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).doCreateBatch(allDetails);
			return flag;
		} catch (Exception e)
		{
			throw e;
		}
		finally
		{
			this.dbc.close();
		}
	}

}
