package lyk.main;

import java.util.ArrayList;
import java.util.List; 
import java.util.Set;

import org.hibernate.Session;

import dbc.HibernateSessionFactory;
import lyk.vo.Item; 
import lyk.vo.News;
import lyk.vo.Subitem; 
public class Hello
{
	public static List<News> allNews = new ArrayList<News>();
	public static void main(String[] args) throws InterruptedException
	{
		Session hibernateSession = HibernateSessionFactory.getSession(); 
		hibernateSession.beginTransaction().commit();
		
		System.out.println("//Main done~~~");
	}
}


