package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
  


import vo.*;
import dbc.*;


public class Main
{	
	public static void main(String[] args) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		
		Groups group = (Groups)session.get(Groups.class, 1);
		group.setTitle("吃串串");
		
		Set<Role> allRoles = group.getRoles();
		
		for(Role role : allRoles)
		{
			if(role.getRid().equals(1))
			{
				role.setTitle("XXXXXX");
			}
		}
		
		session.update(group);
		session.beginTransaction().commit();
		
		
		HibernateSessionFactory.closeSession();
		
		System.out.println("//Main ~~~");
	}
}
