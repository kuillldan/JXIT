package org.lyk.dao.impl;

import org.lyk.dao.IAdminDAO;
import org.springframework.stereotype.Component;

@Component
public class AdminDAOImpl implements IAdminDAO
{

	@Override
	public boolean findLogin()
	{
		System.out.println("=== AdminDAOImpl.findLogin ===");
		return false;
	}

}
