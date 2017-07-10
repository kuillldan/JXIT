package bitool.service;

import bitool.vo.OpenOffManagement;

public interface IOpenOffManagementService
{
	public OpenOffManagement findOpenOffManagement() throws Exception;
	public boolean updateTime(OpenOffManagement vo)throws Exception;
	public boolean updateStatus(String status) throws Exception;
	public boolean updateMode(String mode) throws Exception;
}
