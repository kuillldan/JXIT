package DAO;

import java.sql.Date;
import java.sql.SQLException;

import vo.Admin;

public interface IAdminDAO extends IDAO<String, Admin>
{
	/**
	 * 根据aid查询管理员信息 如果找到则返回Admin记录 否则返回null
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public Admin findFrontLogin(Admin vo) throws SQLException;
	
	public Admin findBackLogin(Admin vo) throws SQLException;
	
	public boolean doUpdateLastDate(String aid, Date date) throws SQLException;
	
	public boolean doUpdatePassword(String aid, String password) throws SQLException;
	
}
