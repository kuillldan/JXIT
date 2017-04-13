package factories;

import service.back.IAdminServiceBack;
import service.back.IGoodsServiceBack;
import service.back.IItemServiceBack;
import service.back.IMemberServiceBack;
import service.back.IOrdersServiceBack;
import service.back.impl.AdminServiceBack;
import service.back.impl.GoodsServiceBackImpl;
import service.back.impl.ItemServiceBackImpl;
import service.back.impl.MemberServiceBackImpl;
import service.back.impl.OrdersServiceBackImpl;

public class ServiceBackFactory
{
	public static IAdminServiceBack getIAdminServiceBackInstance()
	{
		return new AdminServiceBack();
	}
	
	public static IMemberServiceBack getIMemberServiceBackInstance()
	{
		return new MemberServiceBackImpl();
	}
	
	public static IItemServiceBack getIItemServiceBackInstance()
	{
		return new ItemServiceBackImpl();
	}
	
	public static IGoodsServiceBack getIGoodsServiceBackInstance()
	{
		return new GoodsServiceBackImpl();
	}
	
	public static IOrdersServiceBack getIOrdersServiceBackInstance()
	{
		return new OrdersServiceBackImpl();
	}
}
