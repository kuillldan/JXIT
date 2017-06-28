package org.lyk.dao.impl;

import org.lyk.dao.IRoleDAO;
import org.springframework.stereotype.Component;

@Component
public class RoleDAOImpl implements IRoleDAO
{ 
	@Override
	public boolean findAll()
	{
		System.out.println("====RoleDAOImpl.findAll====");
		return true;
	}

}
