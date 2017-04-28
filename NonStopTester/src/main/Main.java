package main;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dbc.HibernateSessionFactory;
import dbc.Tbl01;

public class Main
{
	public static void main(String[] args)
	{
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(" FROM TBL_01 WHERE COL_01_001 = 1 ");
		Tbl01 tbl01 = (Tbl01)query.uniqueResult();
		System.out.println(tbl01);
	}
}
