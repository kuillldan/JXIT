package org.lyk.service.impl;

import javax.annotation.Resource;

import org.lyk.dao.IDeptDAO;
import org.lyk.dao.impl.DeptDAOImpl;
import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements IDeptService
{
	@Resource
	private IDeptDAO deptDAO;
	
	
	@Override
	public boolean add(Dept vo)
	{
		return this.deptDAO.doCreate(vo);
	}

}
