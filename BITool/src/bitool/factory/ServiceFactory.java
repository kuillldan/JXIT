package bitool.factory;

import bitool.service.IAccountManagementService;
import bitool.service.IOpenOffManagementService;
import bitool.service.impl.AccountManagementServiceImpl;
import bitool.service.impl.OpenOffManagementServiceImpl;

public class ServiceFactory
{
	public static IAccountManagementService getAccountManagementServiceInstance()
	{
		return new AccountManagementServiceImpl();
	}
	
	public static IOpenOffManagementService getOpenOffManagementServiceInstance()
	{
		return new OpenOffManagementServiceImpl();
	}
}
