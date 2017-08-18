package factory;

import service.IDeptService;
import service.IEmpService;
import service.impl.DeptServiceImpl;
import service.impl.EmpServiceImpl;

public class ServiceFactory
{
	public static IDeptService getDeptServiceInstance()
	{
		return new DeptServiceImpl();
	}
	public static IEmpService getEmpServiceInstance()
	{
		return new EmpServiceImpl();
	}
}
