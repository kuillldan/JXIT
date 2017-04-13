package service.front.impl;

import java.util.HashMap;
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
	@Override
	public Map<String, Object> listByMember(String mid, Integer currentPage, Integer lineSize)
			throws Exception
	{ 
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Orders> allOrders = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findAllByMember(mid, currentPage, lineSize);
			Integer allOrdersCount = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).getAllCountByMember(mid);
			map.put("allOrders", allOrders);
			map.put("allOrdersCount", allOrdersCount);
			return map;
		}catch(Exception e)
		{
			throw e;
		}
		finally
		{
			this.dbc.close();
		}
	}
	@Override
	public Orders show(String mid, Integer oid) throws Exception
	{
		try
		{
			Orders orders = null;
			orders = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findByIdAndMember(mid, oid);
			List<Details> allDetails = DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).findAllByOrders(orders.getOid());
			orders.setAllDetails(allDetails);
			return orders;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			this.dbc.close();
		}
	}

}
