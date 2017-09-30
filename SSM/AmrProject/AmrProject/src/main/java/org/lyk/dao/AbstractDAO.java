package org.lyk.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lyk.utils.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

public class AbstractDAO extends SqlSessionDaoSupport
{
	public <T> List<T> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize,
			String namespace)
	{
		Map<String, Object> param = new HashMap<>();
		if (!StringUtils.isEmpty(column))
		{
			param.put("column", null);
		} else
		{
			param.put("column", column);
		}
		if (!StringUtils.isEmpty(keyWord))
		{
			param.put("keyWord", "%" + keyWord + "%");
		} else
		{
			param.put("keyWord", "%%");
		}
		param.put("start", (currentPage - 1) * lineSize);
		param.put("lineSize", lineSize);
		return super.getSqlSession().selectList(namespace, param);
	}

	public Integer findAllCount(String column, String keyWord, String namespace)
	{
		Map<String, Object> param = new HashMap<>();
		if (!StringUtils.isEmpty(column))
		{
			param.put("column", null);
		} else
		{
			param.put("column", column);
		}
		if (!StringUtils.isEmpty(keyWord))
		{
			param.put("keyWord", "%" + keyWord + "%");
		} else
		{
			param.put("keyWord", "%%");
		}
		return super.getSqlSession().selectOne(namespace, param);
	}
}
