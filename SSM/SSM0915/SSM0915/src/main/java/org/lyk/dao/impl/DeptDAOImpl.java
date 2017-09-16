package org.lyk.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.dao.IDeptDAO;
import org.lyk.vo.Dept;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("deptDAOImpl")
public class DeptDAOImpl extends SqlSessionDaoSupport implements IDeptDAO
{
	
	@Autowired
	public DeptDAOImpl(SqlSessionFactory sqlSessionFactory)
	{
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public boolean doCreate(Dept dept) throws Exception
	{
		return super.getSqlSession().insert("org.lyk.vo.mapping.DeptNS.doCreate",dept) == 1;
	}

}
