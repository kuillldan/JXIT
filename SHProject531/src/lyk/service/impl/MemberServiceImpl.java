package lyk.service.impl;

import org.hibernate.Session;

import lyk.dbc.HibernateSessionFactory;
import lyk.factory.DAOFactory;
import lyk.service.IMemberService;
import lyk.vo.MemberLogin;

public class MemberServiceImpl implements IMemberService
{
	private Session session = HibernateSessionFactory.getSession();
	@Override
	public boolean insert(MemberLogin login) throws Exception
	{
		try
		{ 
			boolean flag = DAOFactory.getIMemberDAOInstance(session).doCreate(login);
			this.session.beginTransaction().commit();
			return flag; 
		}catch(Exception e)
		{
			throw e;
		}
		finally
		{
			session.close();
		}
	}
	
	

}
