package org.lyk.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.dao.AbstractDAO;
import org.lyk.dao.IActionDAO;
import org.lyk.utils.CommonConstant;
import org.lyk.vo.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionDAOImpl extends AbstractDAO implements IActionDAO
{
	private static final String MAPPING_PREFIX = CommonConstant.MAPPING_PREFIX + "ActionNS.";
	
	@Autowired
	public ActionDAOImpl(SqlSessionFactory sqlSessionFactory)
	{
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	
	@Override
	public List<Action> findAllByGroups(Integer gid) throws Exception
	{
		return super.getSqlSession().selectList(MAPPING_PREFIX + "findAllByGroups",gid); 
	}

}
