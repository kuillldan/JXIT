package bitool.dao;

import bitool.vo.AccountManagement;

public interface IAccountManagementDAO
{
	public AccountManagement findByIpAddress(String ipAddress)  throws Exception;
}
