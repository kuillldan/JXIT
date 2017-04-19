package service.front;
import java.sql.SQLException;
import java.util.Map;

import vo.Admin;

public interface IAdminServiceFront
{
	
	/**
	 * 
	 * @param admin
	 * @return  key = flag 如果flag==true则找到Admin 反之未找到
	 * admin 如果找到，则admin代表该记录 否则admin为null
	 * @throws SQLException
	 */
	public Map<String, Object> login(Admin admin) throws Exception;
	
	public boolean updatePassword(String aid, String oldPassword, String newPassword) throws Exception;
	
	
}
