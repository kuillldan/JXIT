package bitool.service.impl;

import bitool.dbc.BI_DatabaseConnection;
import bitool.factory.DAOFactory;
import bitool.service.IAccountManagementService;
import bitool.vo.AccountManagement;

public class AccountManagementServiceImpl implements IAccountManagementService
{
	private BI_DatabaseConnection dbc = new BI_DatabaseConnection();
	
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
