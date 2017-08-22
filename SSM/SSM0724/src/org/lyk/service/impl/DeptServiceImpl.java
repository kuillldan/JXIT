package org.lyk.service.impl;

import javax.annotation.Resource;

import org.lyk.dao.IDeptDAO;
import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Service;

@Service(value="deptServiceImpl")
public class DeptServiceImpl implements IDeptService
{
	@Resource
	private IDeptDAO deptDaoImpl;
	
	@Override
	public boolean insert(Dept dept)
	{
		return this.deptDaoImpl.doCreate(dept);
	}
}
