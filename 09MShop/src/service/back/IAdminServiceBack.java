package service.back;

import vo.Admin;

public interface IAdminServiceBack
{
	/**
	 * 根据aid和password确定是否登录成功
	 * 如果登录成功则更新lastdate字段
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean login(Admin vo) throws Exception;
}
