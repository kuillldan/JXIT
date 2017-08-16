package factory;

import service.IDeptService;
import service.impl.DeptServiceImpl;

public class ServiceFactory
{
	public static IDeptService getDeptServiceInstance()
	{
		return new DeptServiceImpl();
	}
}
