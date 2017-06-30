package org.lyk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.lyk.dao.INewsDAO;
import org.lyk.service.INewsService;
import org.lyk.vo.News;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements INewsService
{
	@Resource
	private INewsDAO newsDAOImpl;
	
	
	@Override
	public boolean insert(News news) throws Exception
	{
		return this.newsDAOImpl.doCreate(news);
	}

	@Override
	public boolean update(News news) throws Exception
	{
		return this.newsDAOImpl.doUpdate(news);
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception
	{
		if(ids == null || ids.size() <= 0)
			return false;
		
		return this.newsDAOImpl.doRemoveBatch(ids);
	}

	@Override
	public News get(Integer nid) throws Exception
	{
		return this.newsDAOImpl.findById(nid); 
	}

	@Override
	public List<News> list() throws Exception
	{
		return this.newsDAOImpl.findAll();
	}

	@Override
	public Map<String, Object> list(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception
	{
		Map<String, Object> retVal = new HashMap<String, Object>();
		List<News> allNews = this.newsDAOImpl.findAllSplit(column, keyWord, currentPage, lineSize);
		Integer allNewsCount = this.newsDAOImpl.getAllCount(column, keyWord);
		retVal.put("allNews", allNews);
		retVal.put("allNewsCount", allNewsCount);
		return retVal;
	}
	
}
