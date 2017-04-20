package factory;

import service.*;
import service.impl.T01ServiceImpl;

public class ServiceFactory
{
	public static IT01Service getIt01ServiceInstance()
	{
		return new T01ServiceImpl();
	}
}
