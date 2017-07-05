package bitool.service.impl;

import dbc.DatabaseConnection;
import bitool.factory.DAOFactory;
import bitool.service.IAccountManagementService;
import bitool.vo.AccountManagement;

public class AccountManagementServiceImpl implements IAccountManagementService
{
	private DatabaseConnection dbc = new DatabaseConnection();
	
	@Override
	public AccountManagement findAccountByIpAddress(String ipaddress) throws Exception
	{ 
		try
		{
			return DAOFactory.getAccountManagementDAOInstance(this.dbc.getConnection()).findByIpAddress(ipaddress);
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
