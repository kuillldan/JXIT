package cn.mldn.service;

import java.util.List;

import cn.mldn.vo.News;

public interface INewsService {
	public boolean insert(News vo) throws Exception;

	public List<News> list(String column, String keyWord, int currentPage,
			int lineSize) throws Exception;
}
