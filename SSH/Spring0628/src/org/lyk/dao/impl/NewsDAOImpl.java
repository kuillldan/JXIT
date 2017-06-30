package org.lyk.dao.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;




import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.loader.custom.Return;
import org.lyk.dao.INewsDAO;
import org.lyk.vo.News; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class NewsDAOImpl implements INewsDAO
{
	@Resource
    private SessionFactory sessionFactory;
	
	@Override
	public boolean doCreate(News news) throws Exception
	{
		  return this.sessionFactory.getCurrentSession().save(news) != null ;
	}

	@Override
	public boolean doUpdate(News news) throws Exception
	{
		String hql = " UPDATE News SET title=?,pubdate=?,content=? WHERE nid=? ";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, news.getTitle());
		query.setParameter(1, news.getPubdate());
		query.setParameter(2, news.getContent());
		query.setParameter(3, news.getNid());
		return query.executeUpdate() == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception
	{
		if(ids == null  || ids.size() <= 0)
			return false;
		
		
		StringBuffer hql = new StringBuffer(" DELETE FROM News WHERE nid IN( ");
		for(Integer id : ids)
		{
			hql.append(id).append(",");
		}
		hql.delete(hql.length()-1, hql.length());
		hql.append(" )");
		
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.executeUpdate() == ids.size();
	}

	@Override
	public News findById(Integer id) throws Exception
	{
		return (News)this.sessionFactory.getCurrentSession().get(News.class, id);
	}

	@Override
	public List<News> findAll() throws Exception
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(News.class);
		return criteria.list();
	}

	@Override
	public List<News> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception
	{
		String hql = " FROM News AS n WHERE n." + column + " LIKE ? ";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, "%"+keyWord+"%");
		query.setFirstResult((currentPage -1)*lineSize);
		query.setMaxResults(lineSize);
		System.out.println(query.getQueryString() + "column:" + column + ",keyWord:" + keyWord);
		return query.list();
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception
	{
		String hql = " SELECT COUNT(*) FROM News AS n WHERE n." + column + " LIKE ? ";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, "%"+keyWord+"%");
		return ((Long)query.uniqueResult()).intValue();
	}  
}
