package org.lyk.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.dao.IActionDAO;
import org.lyk.utils.CommonConstant;
import org.lyk.vo.Action;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionDAOImpl extends SqlSessionDaoSupport implements IActionDAO
{
	
	private static final String MAPPING_PREFIX = CommonConstant.MAPPING_PREFIX + "ActionNS.";
	
	@Autowired
	public ActionDAOImpl(SqlSessionFactory sqlSessionFactory)
	{
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public boolean doCreate(Action vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Action vo) throws Exception
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
	public Action findById(Integer id) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize)
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
	public List<Action> findAllByGroups(Integer gid) throws Exception
	{
		return super.getSqlSession().selectList(MAPPING_PREFIX + "findAllByGroups", gid);
	}
}