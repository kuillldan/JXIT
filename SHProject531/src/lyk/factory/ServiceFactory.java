package lyk.factory;

import lyk.service.IMemberService;
import lyk.service.impl.MemberServiceImpl;

public class ServiceFactory
{
	public static IMemberService getIMemberServiceInstance()
	{
		return new MemberServiceImpl();
	}
}
