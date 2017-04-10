package org.lyk.service;

import org.lyk.dao.impl.AdminDAOImpl;
import org.lyk.vo.Admin;

public interface IAdminService
{
	public boolean login(Admin admin) throws Exception;
}
