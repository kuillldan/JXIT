package org.lyk.service.impl;

import javax.annotation.Resource;

import org.lyk.dao.IAdminDAO;
import org.lyk.dao.IRoleDAO;
import org.lyk.dao.impl.AdminDAOImpl;
import org.lyk.service.IAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService
{
	@Resource
	private IAdminDAO adminDAOImpl;
	
	@Resource
	private IRoleDAO roleDAOImpl;
	

	@Override
	public boolean login()
	{
		this.adminDAOImpl.findLogin();
		System.out.println("XXXXX");
		this.roleDAOImpl.findAll();
		return true;
	}

}
