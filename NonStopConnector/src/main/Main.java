package main;

import java.util.List;

import utils.General;
import vo.T01;
import factory.ServiceFactory;

public class Main
{

	public static void main(String[] args) throws Exception
	{
		System.out.println("Begin time: " + General.getCurrentTime());
		Object allT01s = ServiceFactory.getIt01ServiceInstance().list();
		System.out.println("End time: " + General.getCurrentTime());
		
		System.out.println(allT01s);

		System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
		System.out.println("///Main done~~");
	}

}
