package org.lyk.service.impl;

import javax.annotation.Resource;

import org.lyk.dao.IDeptDAO;
import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value="deptServiceImpl")
public class DeptServiceImpl implements IDeptService
{
	private static final Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);
	
	@Resource(name="deptDaoImpl")
	private IDeptDAO deptDaoImpl;
	
	@Override
	public boolean insert(Dept dept)
	{
		return this.deptDaoImpl.doCreate(dept);
	}
}
