package service.impl;

import dbc.DatabaseConnection;
import factory.DAOFactory;
import service.ITBL_04Service;

public class TBL_04ServiceImpl implements ITBL_04Service
{
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean insert() throws Exception
	{
		try
		{
			return DAOFactory.getItbl_04daoInstance(this.dbc.getConnection()).doCreate(null);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

}
