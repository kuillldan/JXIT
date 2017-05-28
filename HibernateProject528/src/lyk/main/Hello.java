package lyk.main;

import java.util.ArrayList;
import java.util.List; 

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
		Item item = new Item();
		item.setTitle("�ҵ�"); 
		
		Subitem subItem1 = new Subitem();
		Subitem subItem2 = new Subitem();
		Subitem subItem3 = new Subitem();
		
		subItem1.setTitle("�յ�");
		subItem2.setTitle("����");
		subItem3.setTitle("ϴ�»�");
		
		item.getSubitems().add(subItem1);
		item.getSubitems().add(subItem2);
		item.getSubitems().add(subItem3);
		
		
		hibernateSession.save(item);
		hibernateSession.beginTransaction().commit();
		
		System.out.println("//Main done~~~");
	}
}


