package org.lyk.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.dao.ILevelDAO;
import org.lyk.utils.CommonConstant;
import org.lyk.vo.Level;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LevelDAOImpl extends SqlSessionDaoSupport implements ILevelDAO
{
	private static final String MAPPING_PREFIX = CommonConstant.MAPPING_PREFIX +"LevelNS.";
	@Autowired
	public LevelDAOImpl(SqlSessionFactory sqlSessionFactory)
	{
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public boolean doCreate(Level vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Level vo) throws Exception
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
	public Level findById(Integer id) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Level> findAll() throws Exception
	{
		return super.getSqlSession().selectList(MAPPING_PREFIX + "findAll");
	}

	@Override
	public List<Level> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize)
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

}
