package dao.impl;

import org.springframework.stereotype.Repository;

import vo.Dept;
import dao.IDeptDAO;


@Repository(value="deptDAOImpl")
public class DeptDAOImpl implements IDeptDAO
{
	@Override
	public boolean doCreate(Dept dept)
	{
		System.out.println("向数据库中插入DEPT数据:" + dept);
		return true;
	}

}
