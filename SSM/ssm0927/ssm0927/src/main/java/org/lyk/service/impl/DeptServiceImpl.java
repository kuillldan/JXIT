package org.lyk.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.lyk.dao.IDeptDAO;
import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Service;

@Service("deptServiceImpl")
public class DeptServiceImpl implements IDeptService
{
	@Resource(name="deptDAOImpl")
	private IDeptDAO deptDAOImpl;
	
	@Override
	public boolean insert(Dept dept) throws Exception
	{
		return this.deptDAOImpl.insert(dept);
	}

	@Override
	public List<Dept> list() throws Exception
	{
		return this.deptDAOImpl.findAll();
	}
	
}
