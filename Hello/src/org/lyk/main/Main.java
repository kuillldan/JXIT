package org.lyk.main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.lyk.factory.ServiceFactory;
import org.lyk.vo.Admin;
import org.lyk.vo.T01;

import utils.MD5Code;
import dbc.DatabaseConnection;
import dbc.HibernateSessionFactory;


class DeptWorker implements Runnable
{
	
	
	@Override
	public void run()
	{
		
	}
}


public class Main
{

	public static void main(String[] args) throws Exception
	{ 
		
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(" FROM Admin WHERE aid=:aid ");
		query.setParameter("aid", "liuyuankui");
		List<Admin> allAdmins = query.list();	
		System.out.println(allAdmins);
		System.out.println("\r\n---------------------");
		System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
		;
		System.out.println("///Main done~~");
	}
}
