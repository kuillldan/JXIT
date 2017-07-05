package org.lyk.dao.impl;

import org.lyk.dao.IDeptDAO;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Repository;

@Repository
public class DeptDAOImpl implements IDeptDAO
{

	@Override
	public boolean doCreate(Dept dept)
	{
		System.out.println("【数据层】创建dept:" + dept);
		return false;
	}
}
