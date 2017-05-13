package factory;

import java.lang.reflect.Proxy;

import service.IDeptService;
import service.impl.DeptServiceImpl;

public class ServiceFactory
{
	public static IDeptService getIDeptServiceInstance()
	{
		Object realObject = new DeptServiceImpl();
		ServiceInvocationHandler h = new ServiceInvocationHandler(realObject);
		IDeptService deptServiceInstance = (IDeptService)Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(), h);
		return deptServiceInstance;
	}
	
	
}
