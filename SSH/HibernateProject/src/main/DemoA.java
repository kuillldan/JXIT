package main;

import org.hibernate.Session;
import org.hibernate.cache.ehcache.EhCacheRegionFactory;

import vo.Member;
import dbc.HibernateSessionFactory;

public class DemoA
{

	public static void main(String[] args) throws InterruptedException
	{
		Session session = HibernateSessionFactory.getSession();
		for (int i = 0; i < 10; i++)
		{
			Member member  = (Member)session.get(Member.class, "21591930");
			System.out.println(member);
			Thread.sleep(3000);
			//session = HibernateSessionFactory.getSessionFactory().openSession();
			session.clear(); 
		}
		System.out.println("//DemoA done~~~");
	}

}