package lyk.factory;

import org.hibernate.Session;

import lyk.dao.IMemberDAO;
import lyk.dao.impl.MemberDAOImpl;

public class DAOFactory
{
	public static IMemberDAO getIMemberDAOInstance(Session session)
	{
		return new MemberDAOImpl(session);
	}
}
