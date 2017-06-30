package org.lyk.dao;

import java.util.*;

import org.lyk.vo.*; 

public interface INewsDAO
{
	public boolean doCreate(News news) throws Exception;
	public boolean doUpdate(News news) throws Exception;
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception;
	public News findById(Integer id) throws Exception;
	public List<News> findAll()  throws Exception;
	public List<News> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize)  throws Exception;
	public Integer getAllCount(String column, String keyWord)  throws Exception;
}
