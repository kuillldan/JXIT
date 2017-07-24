package cn.mldn.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.mldn.dao.INewsDAO;
import cn.mldn.vo.News;

@Component
public class NewsDAOImpl extends SqlSessionDaoSupport implements INewsDAO {
	@Autowired 
	public NewsDAOImpl(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public boolean doCreate(News vo) throws Exception {
		return super.getSqlSession().insert(
				"cn.mldn.vo.mapping.NewsNS.doCreate", vo) > 0;
	}

	@Override
	public List<News> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", column);
		map.put("keyWord", "%" + keyWord + "%");
		map.put("start", (currentPage - 1) * lineSize);
		map.put("lineSize", lineSize);
		return super.getSqlSession().selectList(
				"cn.mldn.vo.mapping.NewsNS.findAllSplit", map);
	} 

}
