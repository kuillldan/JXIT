package org.lyk.dao.impl;

import org.lyk.dao.IDeptDAO;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Repository;


@Repository(value="deptDAOImpl")
public class DeptDAOImpl implements IDeptDAO
{
	public boolean doCreate(Dept dept)
	{
		System.out.println("向数据库中插入DEPT数据:" + dept);
		return true;
	}

}
