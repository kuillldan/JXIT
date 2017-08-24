package org.lyk.dao.impl;

import org.lyk.dao.IDeptDAO;
import org.lyk.vo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository(value="deptDaoImpl")
public class DeptDAOImpl implements IDeptDAO
{
	private static final Logger logger = LoggerFactory.getLogger(DeptDAOImpl.class);

	@Override
	public boolean doCreate(Dept dept)
	{
		logger.info("【数据层】向数据库插入一条数据:" + dept);
		return true;
	}

}
