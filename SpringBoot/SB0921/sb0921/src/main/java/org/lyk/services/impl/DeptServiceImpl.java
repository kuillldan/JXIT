package org.lyk.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.lyk.dao.IDeptDAO;
import org.lyk.services.IDeptService;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptServiceImpl implements IDeptService
{
	@Resource
	private IDeptDAO deptDAO;
	
	@Override
	public List<Dept> list() throws Exception
	{
		return this.deptDAO.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public boolean doCreate(List<Dept> allDepts) throws Exception
	{
		this.deptDAO.doCreate(allDepts.get(0));
	
		this.deptDAO.doCreate(allDepts.get(1));
		return true;
	}

}
