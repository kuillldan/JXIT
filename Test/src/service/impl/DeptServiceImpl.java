package service.impl;

import java.util.List;
import java.util.Set;

import dbc.DatabaseConnection;
import factory.DAOFactory;
import service.IDeptService;
import vo.Dept;

public class DeptServiceImpl implements IDeptService
{
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public Dept show(Integer did) throws Exception
	{
		try
		{
			Dept dept = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(did);
			return dept;
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
