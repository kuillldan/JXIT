package cn.mldn.dao;

import java.util.List;

import cn.mldn.vo.News;

public interface INewsDAO {
	public boolean doCreate(News vo) throws Exception;

	public List<News> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception;
}
