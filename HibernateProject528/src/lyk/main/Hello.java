package lyk.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import dbc.HibernateSessionFactory;
import lyk.vo.Item;
import lyk.vo.MemberDetails;
import lyk.vo.MemberLogin;
import lyk.vo.News;
import lyk.vo.Subitem;

public class Hello
{ 
	public static void main(String[] args) throws InterruptedException
	{
		List<String> allMsg = new ArrayList<String>();
		

		for (Object msg : allMsg)
		{
			System.out.println("[类型:" + msg.getClass().getSimpleName() + ",值:" + msg + "]");
		}

		System.out.println("//Main done~~~");
	}

	public static void change(List allObjs)
	{
		allObjs.set(1, 3232);
	}
}
