package org.lyk.service.impl;

import javax.annotation.Resource;

import org.lyk.dao.IAdminDAO;
import org.lyk.service.IAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService
{
	@Resource
	private IAdminDAO adminDAO;
	 
	public void setAdminDAO(IAdminDAO adminDAO)
	{
		this.adminDAO = adminDAO;
	}

	@Override
	public boolean login()
	{
		this.adminDAO.findLogin();
		return false;
	}

}
