package factories;
 
import service.fornt.IGoodsServiceFront; 
import service.fornt.impl.GoodsServiceFrontImpl;


public class ServiceFrontFactory
{
	 
	
	public static IGoodsServiceFront getIGoodsServiceFrontInstance()
	{
		return new GoodsServiceFrontImpl();
	} 
}
