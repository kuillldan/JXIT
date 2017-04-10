package org.lyk.factory;

import org.lyk.service.IAdminService;
import org.lyk.service.IDeptService;
import org.lyk.service.IEmpService;
import org.lyk.service.impl.AdminServiceImpl;
import org.lyk.service.impl.DeptServiceImpl;
import org.lyk.service.impl.EmpServiceImpl;

public class ServiceFactory
{
	public static IDeptService getIDeptServiceInstance()
	{
		return new DeptServiceImpl();
	}
	
	public static IEmpService getIEmpServiceInstance()
	{
		return new EmpServiceImpl();
	}
	
	public static IAdminService getIAdminServiceInstance()
	{
		return new AdminServiceImpl();
	}
}