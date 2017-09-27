package org.lyk.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.dao.IDeptDAO;
import org.lyk.utils.CommonConstant;
import org.lyk.vo.Dept;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("deptDAOImpl")
public class DeptDAOImpl extends SqlSessionDaoSupport implements IDeptDAO
{
	private static final String MAPPER_NAMESPACE = CommonConstant.MAPPING_PREFIX + "DeptNS.";
	
//	@Resource(name="sqlSessionFactory")
//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	public DeptDAOImpl(SqlSessionFactory sqlSessionFactory)
	{
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public boolean insert(Dept dept) throws Exception
	{
		return super.getSqlSession().insert(MAPPER_NAMESPACE + "doCreate",dept) == 1;
	}

	@Override
	public List<Dept> findAll() throws Exception
	{
		return super.getSqlSession().selectList(MAPPER_NAMESPACE + "findAll");
	}
}
