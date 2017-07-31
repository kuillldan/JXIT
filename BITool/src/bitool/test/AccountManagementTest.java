package bitool.test;

import static org.junit.Assert.*;

import org.junit.Test;

import bitool.service.IAccountManagementService;
import bitool.service.impl.AccountManagementServiceImpl;

public class AccountManagementTest
{

	@Test
	public void findAccountByIpAddress()
	{
		String ipAddress = "127.0.0.1";
		IAccountManagementService accountManagementService = new AccountManagementServiceImpl();
		try
		{
			assertNotNull(accountManagementService.findAccountByIpAddress(ipAddress));
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findAccountByIpAddress1()
	{
		String ipAddress = "127.0.0.2";
		IAccountManagementService accountManagementService = new AccountManagementServiceImpl();
		try
		{
			assertNull(accountManagementService.findAccountByIpAddress(ipAddress));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
