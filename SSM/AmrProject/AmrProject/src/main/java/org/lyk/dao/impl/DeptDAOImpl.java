package org.lyk.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.constant.CommonConstant;
import org.lyk.dao.AbstractDAO;
import org.lyk.dao.IDeptDAO;
import org.lyk.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeptDAOImpl extends AbstractDAO implements IDeptDAO
{
	private static final String MAPPING_PREFIX = CommonConstant.MAPPING_PREFIX + "DeptNS.";

	@Autowired
	public DeptDAOImpl(SqlSessionFactory sqlSessionFactory)
	{
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public boolean doCreate(Dept vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Dept vo) throws Exception
	{
		return super.getSqlSession().update(MAPPING_PREFIX + "doUpdate", vo) == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dept findById(Integer id) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> findAll() throws Exception
	{
		return super.getSqlSession().selectList(MAPPING_PREFIX + "findAll");
	}

	@Override
	public List<Dept> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception
	{
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> listAllDeptBySflag(Integer sflag) throws Exception
	{
		return super.getSqlSession().selectList(MAPPING_PREFIX + "listAllDeptBySflag",sflag);
	}

}
