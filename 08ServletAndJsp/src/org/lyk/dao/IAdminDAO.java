package org.lyk.dao;

import org.lyk.vo.Admin;

public interface IAdminDAO extends IDAO<String, Admin>
{
	public boolean findLogin(Admin admin) throws Exception;
}
