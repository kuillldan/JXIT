package factories;

import service.front.IAdminServiceFront;
import service.front.IEmployeeServiceFront;
import service.front.impl.AdminServiceFrontImpl;
import service.front.impl.EmployeeServiceFrontImpl;

public class ServiceFrontFactory
{
	public static IAdminServiceFront getIAdminServiceFrontInstance()
	{
		return new AdminServiceFrontImpl();
	}
	public static IEmployeeServiceFront getIEmployeeServiceFrontInstance()
	{
		return new EmployeeServiceFrontImpl();
	}
}
