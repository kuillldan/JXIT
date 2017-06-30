package org.lyk.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.lyk.vo.News;

public interface INewsService
{
	public boolean insert(News news) throws Exception;
	public boolean update(News news) throws Exception;
	public boolean delete(Set<Integer> ids) throws Exception;
	public News get(Integer nid) throws Exception;
	public List<News> list() throws Exception;
	public Map<String, Object> list(String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception;
}
