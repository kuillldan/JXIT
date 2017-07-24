package cn.mldn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dao.INewsDAO;
import cn.mldn.service.INewsService; 
import cn.mldn.vo.News;

@Service
public class NewsServiceImpl implements INewsService {
	@Resource
	private INewsDAO newsDAO;

	@Override
	public boolean insert(News vo) throws Exception {
		return this.newsDAO.doCreate(vo);
	}

	@Override
	public List<News> list(String column, String keyWord, int currentPage,
			int lineSize) throws Exception {
		return this.newsDAO
				.findAllSplit(column, keyWord, currentPage, lineSize);
	}

}
