package main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.dom4j.DocumentException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import dbc.HibernateSessionFactory;
import utils.HibernateConfiguation;
import utils.MySession;
import vo.Member;
import vo.News;

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
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(" from Member  where  mid like ? ");
		query.setParameter(0, "%2159192%");
		Iterator<Member> iter = query.iterate();
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		session.close();
		System.out.println("//Main ~~~");
	}

}
