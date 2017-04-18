package service.front.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.front.IAdminServiceFront;
import vo.Admin;

public class AdminServiceFrontImpl implements IAdminServiceFront
{
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public Map<String, Object> login(Admin admin) throws SQLException
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			Admin result = DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(admin); 
			map.put("flag", result != null);
			map.put("admin", result);
			return map;
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
