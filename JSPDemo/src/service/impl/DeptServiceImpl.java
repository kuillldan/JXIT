package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import dbc.DatabaseConnection;
import factory.DAOFactory;
import service.IDeptService;
import vo.Dept;

public class DeptServiceImpl implements IDeptService
{
	Connection conn = DatabaseConnection.getConnection();
	
	@Override
	public boolean insert(Dept dept) throws Exception
	{
		try
		{
			return DAOFactory.getDeptDAOInstance(conn).doCreate(dept);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(conn);
		}
	}

	@Override
	public boolean remove(Set<Integer> ids) throws Exception
	{
		try
		{
			return DAOFactory.getDeptDAOInstance(conn).doRemoveBatch(ids);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(conn);
		}
	}

	@Override
	public Dept updatePre(Integer id) throws Exception
	{
		try
		{
			return DAOFactory.getDeptDAOInstance(conn).findById(id);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(conn);
		}
	}

	@Override
	public boolean update(Dept dept) throws Exception
	{
		try
		{
			return DAOFactory.getDeptDAOInstance(conn).doUpdate(dept);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(conn);
		}
	}

	@Override
	public Dept findById(Integer deptno) throws Exception
	{
		try
		{
			return DAOFactory.getDeptDAOInstance(conn).findById(deptno);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(conn);
		}
	}

	@Override
	public List<Dept> list() throws Exception
	{
		try
		{
			return DAOFactory.getDeptDAOInstance(conn).findAll();
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(conn);
		}
	}

}
