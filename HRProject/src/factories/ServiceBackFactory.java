package factories;

import service.back.IAdminServiceBack;
import service.back.impl.AdminServiceBackImpl;
import service.front.impl.AdminServiceFrontImpl;

public class ServiceBackFactory
{
	public static IAdminServiceBack getIAdminServiceBackInstance()
	{
		return new AdminServiceBackImpl();
	}
}