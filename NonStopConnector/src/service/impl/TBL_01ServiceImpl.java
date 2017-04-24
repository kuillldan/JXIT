package service.impl;

import java.util.List;
 



import service.ITBL_01Service;
import vo.TBL_01;
import dbc.DatabaseConnection;
import factory.DAOFactory;

public class TBL_01ServiceImpl implements ITBL_01Service
{
	DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public List<TBL_01> list() throws Exception
	{
		try
		{
			List<TBL_01> allT01s = DAOFactory.getIt01daoInstance(this.dbc.getConnection()).findAll();
			return allT01s;
			
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}
	@Override
	public boolean insert() throws Exception
	{
		try
		{
			return DAOFactory.getIt01daoInstance(this.dbc.getConnection()).doCreate(null);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

}
