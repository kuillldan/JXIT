package factories;

import service.back.IAdminServiceBack;
import service.back.IDeptServiceBack;
import service.back.IJobsServiceBack;
import service.back.ILevelServiceBack;
import service.back.impl.AdminServiceBackImpl;
import service.back.impl.DeptServiceBackImpl;
import service.back.impl.JobsServiceBackImpl;
import service.back.impl.LevelServiceBackImpl;
import service.front.impl.AdminServiceFrontImpl;

public class ServiceBackFactory
{
	public static IAdminServiceBack getIAdminServiceBackInstance()
	{
		return new AdminServiceBackImpl();
	}
	
	public static IDeptServiceBack getIDeptServiceBackInstance()
	{
		return new DeptServiceBackImpl();
	}
	
	public static IJobsServiceBack getIJobsServiceBackInstance()
	{
		return new JobsServiceBackImpl();
	}
	
	public static ILevelServiceBack getILevelServiceBackInstance()
	{
		return new LevelServiceBackImpl();
	}
}
