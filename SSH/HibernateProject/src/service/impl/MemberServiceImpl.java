package service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.IMemberDAO;
import dbc.HibernateSessionFactory;
import factory.DAOFactory;
import service.IMemberService;
import vo.Member;

public class MemberServiceImpl implements IMemberService
{ 
	@Override
	public boolean insert(Member vo) throws Exception
	{  
		Session session = HibernateSessionFactory.getSession();
		
		try
		{
			Transaction trans = session.beginTransaction();
			boolean flag = DAOFactory.getIMemberDAOInstance().doCreate(vo);
			trans.commit();
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
