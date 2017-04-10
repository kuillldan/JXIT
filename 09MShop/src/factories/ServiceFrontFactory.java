package factories;

import service.front.IGoodsServiceFront;
import service.front.IMemberServiceFront;
import service.front.IShopCarServiceFront;
import service.front.impl.GoodsServiceFrontImpl;
import service.front.impl.MemberServiceFrontImpl;
import service.front.impl.ShopCarServiceFrontImpl;

public class ServiceFrontFactory
{
	public static IMemberServiceFront getMemberServiceFrontInstance()
	{
		return new MemberServiceFrontImpl();
	}
	
	public static IGoodsServiceFront getIGoodsServiceFrontInstance()
	{
		return new GoodsServiceFrontImpl();
	}
	
	public static IShopCarServiceFront getIShopCarServiceFrontInstance()
	{
		return new ShopCarServiceFrontImpl();
	}
}
