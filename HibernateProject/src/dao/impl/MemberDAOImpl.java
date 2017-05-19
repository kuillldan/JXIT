package dao.impl;

import org.hibernate.Session;

import vo.Member;
import dao.IMemberDAO;
import dbc.HibernateSessionFactory;

public class MemberDAOImpl implements IMemberDAO
{
	@Override
	public boolean doCreate(Member vo) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		return session.save(vo) != null; 
	}

}
