package service.back.impl;

import java.util.List;
import java.util.Set;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.back.IItemServiceBack;
import vo.Item;

public class ItemServiceBackImpl implements IItemServiceBack
{
	DatabaseConnection dbc = new DatabaseConnection();
	
	@Override
	public boolean insert(Item vo) throws Exception
	{
		try
		{
			return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doCreate(vo);
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

	@Override
	public boolean update(Item vo) throws Exception
	{
		try
		{
			return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doUpdate(vo);
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

	@Override
	public boolean delete(Set<Integer> ids) throws Exception
	{
		try
		{
			return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
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

	@Override
	public List<Item> list() throws Exception
	{
		try
		{
			List<Item> allItems = DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll();
			return allItems;
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
