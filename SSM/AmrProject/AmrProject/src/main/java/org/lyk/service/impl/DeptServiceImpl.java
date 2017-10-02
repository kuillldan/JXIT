package org.lyk.service.impl;

import java.util.ArrayList;
import java.util.List;

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
	private IDeptDAO deptDAOImpl;

	@Override
	public List<Dept> list() throws Exception
	{
		return this.deptDAOImpl.findAll();
	}

	@Override
	public boolean updateTitleByDid(Dept dept) throws Exception
	{
		return this.deptDAOImpl.doUpdate(dept);
	}

}
