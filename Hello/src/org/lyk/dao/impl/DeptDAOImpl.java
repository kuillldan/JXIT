package org.lyk.dao.impl;

import java.util.List;

import org.lyk.dao.IDeptDAO;
import org.lyk.vo.Dept;

public class DeptDAOImpl implements IDeptDAO
{

	@Override
	public boolean doCreate(Dept vo) throws Exception
	{
		System.out.println("====向Dept插入一条数据====");
		return true;
	}

	@Override
	public List<Dept> findAll() throws Exception
	{
		System.out.println("从数据库中查询所有的Dept记录");
		return null;
	}

}
