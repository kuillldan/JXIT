package service.impl;

import dbc.DatabaseConnection;
import factory.DAOFactory;
import service.ITBL_03Service;
import vo.TBL_03;

public class TBL_03ServiceImpl implements ITBL_03Service
{
	DatabaseConnection dbc = new DatabaseConnection();
	
	@Override
	public TBL_03 findById(String COL_03_001) throws Exception
	{
		try
		{
			return DAOFactory.getIt03daoInstance(this.dbc.getConnection()).findById(COL_03_001);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

}
