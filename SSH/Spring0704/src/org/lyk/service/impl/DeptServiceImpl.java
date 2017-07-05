package org.lyk.service.impl;

import javax.annotation.Resource;

import org.lyk.dao.IDeptDAO;
import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements IDeptService
{ 
	@Resource(name="deptDAOImpl2")
	private IDeptDAO xxxfff;
	
	@Override
	public boolean insert(Dept dept)
	{
		this.xxxfff.doCreate(dept);
		return false;
	}

}