package org.lyk.main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.lyk.factory.ServiceFactory;
import org.lyk.vo.Admin;
import org.lyk.vo.Book;
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
		Book b1 = new Book("JAVA变成死相", 99.9);
		Book b2 = new Book("Head First JSP", 68.5);
		Set<Book> allBooks = new TreeSet<Book>();
		allBooks.add(b1);
		allBooks.add(b2);
		Iterator<Book> iter = allBooks.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		System.out.println("///Main done~~");
	}
}
