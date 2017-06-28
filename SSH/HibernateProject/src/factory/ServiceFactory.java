package factory;

import service.IMemberService;
import service.impl.MemberServiceImpl;

public class ServiceFactory
{
	public static IMemberService getIMemberServiceInstance()
	{
		return new MemberServiceImpl();
	}
}
