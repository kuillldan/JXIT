package main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
 




import vo.Member;
import vo.MemberDetails;
import vo.MemberId;
import vo.MemberLogin;
import dbc.HibernateSessionFactory;


public class Main
{	
	public static void main(String[] args) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		MemberLogin login = (MemberLogin)session.get(MemberLogin.class, "mldn-complex");
		System.out.println(login);
		HibernateSessionFactory.closeSession();
		
		System.out.println("//Main ~~~");
	}
}
