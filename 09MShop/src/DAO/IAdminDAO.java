package DAO;

import vo.Admin;

public interface IAdminDAO extends IDAO<String, Admin>
{
	/**
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean findLogin(Admin vo) throws Exception;
	
	/**
	 * 根据当前时间更新admin的lastdate字段
	 * @param aid
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateLastdate(String aid) throws Exception;
}
