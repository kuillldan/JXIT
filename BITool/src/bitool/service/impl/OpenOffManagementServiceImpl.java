package bitool.service.impl;

import dbc.DatabaseConnection;
import bitool.dao.IOpenOffManagement;
import bitool.factory.DAOFactory;
import bitool.service.IOpenOffManagementService;
import bitool.vo.OpenOffManagement;

public class OpenOffManagementServiceImpl implements IOpenOffManagementService
{
	DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public OpenOffManagement findOpenOffManagement() throws Exception
	{
		try
		{
			return DAOFactory.getOpenOffManagementDAOInstance(this.dbc.getConnection()).find();
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
	public boolean updateTime(OpenOffManagement vo) throws Exception
	{
		try
		{
			return DAOFactory.getOpenOffManagementDAOInstance(this.dbc.getConnection()).updateTime(vo);
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
	public boolean updateStatus(String status) throws Exception
	{
		try
		{
			return DAOFactory.getOpenOffManagementDAOInstance(this.dbc.getConnection()).updateStatus(status);
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
