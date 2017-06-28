package main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import vo.Member;
import dbc.HibernateSessionFactory;

public class DemoB
{

	public static void main(String[] args) throws InterruptedException
	{
		Session sessionB = HibernateSessionFactory.getSession();
		System.out.println("DemoB:" + sessionB.hashCode());
		Thread.sleep(10000);
		sessionB.beginTransaction();
		System.out.println("//Main done~~~");
	}

}
