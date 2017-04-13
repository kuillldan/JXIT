package service.back.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.back.IOrdersServiceBack;
import vo.Details;
import vo.Orders;

public class OrdersServiceBackImpl implements IOrdersServiceBack
{
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public Map<String, Object> list(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception
	{
		try
		{
			List<Orders> allOrders = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findAllSplit(
					currentPage, lineSize, column, keyWord);
			Integer allOrdersCount = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).getAllCount(
					column, keyWord);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allOrders", allOrders);
			map.put("allOrdersCount", allOrdersCount);
			return map;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public Orders show(Integer oid) throws Exception
	{
		try
		{
			Orders orders = null;
			orders = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findById(oid);
			if(orders == null)
				return null;
			
			List<Details> allDetails = DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).findAllByOrders(oid);
			orders.setAllDetails(allDetails);
			return orders;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

}
