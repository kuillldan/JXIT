package service.back.impl;

import java.util.List;
import java.util.Set;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.back.IDeptServiceBack;
import vo.Dept;

public class DeptServiceBackImpl implements IDeptServiceBack
{
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean insert(Dept dept) throws Exception
	{
		try
		{
			if(DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findByDname(dept.getDname()))
				return false;
			else
			{
				return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doCreate(dept);
			}
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception
	{
		try
		{
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Dept dept) throws Exception
	{
		try
		{
			if(DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findByDnameAndId(dept.getDname(), dept.getDid()))
			{
				return false;
			}
			else
			{
				return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdate(dept);
			}
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public List<Dept> list() throws Exception
	{
		try
		{
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

}
