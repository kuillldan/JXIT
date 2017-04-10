package org.lyk.service.impl;

import org.lyk.dbc.DatabaseConnection;
import org.lyk.factory.DAOFactory;
import org.lyk.service.IAdminService;
import org.lyk.vo.Admin;

public class AdminServiceImpl implements IAdminService
{
	DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public boolean login(Admin admin) throws Exception
	{
		try
		{
			return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(admin);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

}
