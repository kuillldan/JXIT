package main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.dom4j.DocumentException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import dbc.HibernateSessionFactory;
import utils.HibernateConfiguation;
import utils.MySession;
import vo.Member;

class Worker implements Runnable
{
	private Session session;
	private long timespan;
	public Worker(Session session, long timespan)
	{
		this.session = session;
		this.timespan = timespan;
	}
	
	@Override
	public void run()
	{ 
		session.beginTransaction();
		try
		{
			Thread.sleep(this.timespan);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		session.close();
	}
}



public class Main
{	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ParseException, SQLException, InterruptedException
	{
		org.hibernate.SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		
		Worker worker1 = new Worker(sessionFactory.openSession() , 5000);
		Worker worker2 = new Worker(sessionFactory.openSession() , 1000);
		
		Thread thread1 = new Thread(worker1);
		Thread thread2 = new Thread(worker2);
		
		thread1.start();
		thread2.start();
		
		thread1.join();
		thread2.join();
		
		System.out.println("//Main ~~~");
	}

}
