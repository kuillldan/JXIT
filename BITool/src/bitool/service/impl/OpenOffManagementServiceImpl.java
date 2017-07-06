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
}
