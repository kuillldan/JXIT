package lyk.main;

import java.util.ArrayList;
import java.util.List;

import dbc.HibernateSessionFactory;
import lyk.vo.News;

public class Hello2
{
	public static List<News> allNews = new ArrayList<News>();
	public static void main(String[] args)
	{
		System.out.println(HibernateSessionFactory.getSessionFactory().openSession().hashCode());
//		System.out.println(HibernateSessionFactory.getSessionFactory().openSession().hashCode());
//		System.out.println(HibernateSessionFactory.getSessionFactory().openSession().hashCode());
		System.out.println("//Main done~~~");
	}
}