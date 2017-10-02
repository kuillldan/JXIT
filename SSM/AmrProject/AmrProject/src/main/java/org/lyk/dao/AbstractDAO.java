package org.lyk.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.constant.CommonConstant;
import org.lyk.utils.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public abstract class AbstractDAO extends SqlSessionDaoSupport
{ 
	public <T> List<T> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize,
			String namespace)
	{
		Map<String, Object> param = new HashMap<>();
		if (StringUtils.isEmpty(column))
		{
			param.put("column", null);
		} else
		{
			param.put("column", column);
		}
		if (StringUtils.isEmpty(keyWord))
		{
			param.put("keyWord", "%%");
		} else
		{
			param.put("keyWord", "%" + keyWord + "%");
		}
		param.put("start", (currentPage - 1) * lineSize);
		param.put("lineSize", lineSize);
		CommonConstant.LOGGER.debug(param.toString());
		return this.getSqlSession().selectList(namespace, param);
	}

	public Integer findAllCount(String column, String keyWord, String namespace)
	{
		Map<String, Object> param = new HashMap<>();
		if (StringUtils.isEmpty(column))
		{
			param.put("column", null);
		} else
		{
			param.put("column", column);
		}
		if (StringUtils.isEmpty(keyWord))
		{
			param.put("keyWord", "%%");
		} else
		{
			param.put("keyWord", "%" + keyWord + "%");
		}
		return this.getSqlSession().selectOne(namespace, param);
	}
}
