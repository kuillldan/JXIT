package lyk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.hibernate.Session;

import lyk.dao.IMemberDAO;
import lyk.dbc.HibernateSessionFactory;
import lyk.vo.MemberLogin;

public class MemberDAOImpl implements IMemberDAO
{ 
	 
	private Session session;
	public MemberDAOImpl(Session session)
	{
		this.session = session;
	}
	
	@Override
	public boolean doCreate(MemberLogin login) throws Exception
	{
		return session.save(login) != null;
	}

}
