package factories;
 
import service.fornt.IGoodsServiceFront;
import service.front.impl.GoodsServiceFrontImpl;


public class ServiceFrontFactory
{
	 
	
	public static IGoodsServiceFront getIGoodsServiceFrontInstance()
	{
		return new GoodsServiceFrontImpl();
	} 
}
