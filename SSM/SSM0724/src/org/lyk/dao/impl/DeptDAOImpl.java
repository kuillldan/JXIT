package org.lyk.dao.impl;

import org.lyk.dao.IDeptDAO;
import org.lyk.vo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository(value="deptDaoImplxxx")
public class DeptDAOImpl implements IDeptDAO
{
	private static Logger logger = LoggerFactory.getLogger(DeptDAOImpl.class);
	@Override
	public boolean doCreate(Dept dept)
	{
		logger.info(dept.toString());
		return true;
	}

}
