package bitool.dao;

import bitool.vo.OpenOffManagement;

public interface IOpenOffManagement
{
	public OpenOffManagement find() throws Exception;
	public boolean updateTime(OpenOffManagement vo) throws Exception;
	//public boolean updateStatus(String status) throws Exception;
	public boolean updateMode(String mode) throws Exception;
}
