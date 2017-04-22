package service.back;

import java.util.Map;

import vo.Admin;

public interface IAdminServiceBack
{
	public Map<String, Object> login(Admin admin) throws Exception;
	
	
	public boolean updatePassword(String aid, String oldPassword, String newPassword) throws Exception;
}
