package org.lyk.factory;

import org.lyk.service.IT01Service;
import org.lyk.service.impl.T01ServiceImpl;

public class ServiceFactory
{
	public static IT01Service getIt01ServiceInstance()
	{
		return new T01ServiceImpl();
	}
}
