package org.lyk.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.constant.CommonConstant;
import org.lyk.dao.AbstractDAO;
import org.lyk.dao.IEmpDAO;
import org.lyk.utils.StringUtils;
import org.lyk.vo.Emp;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpDAOImpl extends AbstractDAO implements IEmpDAO
{
	private static final String MAPPING_PREFIX = CommonConstant.MAPPING_PREFIX + "EmpNS.";
	
	@Autowired
	public EmpDAOImpl(SqlSessionFactory sqlSessionFactory)
	{
		super.setSqlSessionFactory(sqlSessionFactory);
	}
 
	@Override
	public boolean doCreate(Emp vo) throws Exception
	{
		return super.getSqlSession().insert(MAPPING_PREFIX + "doCreate", vo) == 1;
	}

	@Override
	public boolean doUpdate(Emp vo) throws Exception
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
	public Emp findById(Integer id) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emp> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emp> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception
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
	public boolean findLogin(Emp emp)
	{
		Emp result = super.getSqlSession().selectOne(MAPPING_PREFIX + "findLogin", emp);
		if (result != null)
		{
			emp.setName(result.getName());
			emp.setPhoto(result.getPhoto());
			emp.setAflag(result.getAflag());
			emp.setDept(result.getDept());
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public List<Emp> findAllAdmin(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception
	{
		return super.findAllSplit(column, keyWord, currentPage, lineSize, MAPPING_PREFIX + "findAllAdmin");
	}

	@Override
	public Integer findAllAdminCount(String column, String keyWord) throws Exception
	{
		return super.findAllCount(column, keyWord, MAPPING_PREFIX + "findAllAdminCount");
	}
}
