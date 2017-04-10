package service.back.impl;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.back.IAdminServiceBack;
import vo.Admin;

public class AdminServiceBack implements IAdminServiceBack
{
	DatabaseConnection dbc = new DatabaseConnection();
	
	@Override
	public boolean login(Admin vo) throws Exception
	{
		try
		{
			if(DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(vo))
			{
				return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doUpdateLastdate(vo.getAid());
			}
			else
			{
				return false;
			}
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
