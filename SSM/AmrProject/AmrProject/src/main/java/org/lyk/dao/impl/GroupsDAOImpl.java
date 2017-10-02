package org.lyk.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.constant.CommonConstant;
import org.lyk.dao.AbstractDAO;
import org.lyk.dao.IGroupsDAO;
import org.lyk.vo.Groups;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupsDAOImpl extends AbstractDAO implements IGroupsDAO
{
	private static final String MAPPING_PREFIX = CommonConstant.MAPPING_PREFIX + "GroupsNS.";
	
	@Autowired
	public GroupsDAOImpl(SqlSessionFactory sqlSessionFactory)
	{
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public boolean doCreate(Groups vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Groups vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Groups findById(Integer id) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Groups> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Groups> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Groups> findAllByDept(Integer did) throws Exception
	{
		return super.getSqlSession().selectList(MAPPING_PREFIX+"findAllByDept", did);  
	}
}
