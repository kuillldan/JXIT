package org.lyk.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.dao.IDeptDAO;
import org.lyk.vo.Dept;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository(value="deptDaoImpl")
public class DeptDAOImpl extends SqlSessionDaoSupport implements IDeptDAO
{
	private static final Logger logger = LoggerFactory.getLogger(DeptDAOImpl.class);
	private static final String MAPPING = "org.lyk.vo.mapping.DeptNS.";
	
	@Autowired
	public DeptDAOImpl(SqlSessionFactory sqlSessionFactory)
	{
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public boolean doCreate(Dept dept)
	{
		return super.getSqlSession().insert(MAPPING + "doCreate",dept) > 0;
	} 
}
