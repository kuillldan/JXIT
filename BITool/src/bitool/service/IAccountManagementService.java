package bitool.service;

import bitool.vo.AccountManagement;

public interface IAccountManagementService
{
	public AccountManagement findAccountByIpAddress(String ipaddress) throws Exception;
}
