package factory;

import dao.IMemberDAO;
import dao.impl.MemberDAOImpl;

public class DAOFactory
{
	public static IMemberDAO getIMemberDAOInstance()
	{
		return new MemberDAOImpl();
	}
}
