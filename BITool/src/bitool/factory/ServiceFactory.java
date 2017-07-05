package bitool.factory;

import bitool.service.IAccountManagementService;
import bitool.service.impl.AccountManagementServiceImpl;

public class ServiceFactory
{
	public static IAccountManagementService getAccountManagementServiceInstance()
	{
		return new AccountManagementServiceImpl();
	}
}
