package service.front;

import java.util.List;
import java.util.Map;

import vo.Details;
import vo.Orders;

public interface IOrdersServiceFront
{
	/**
	 * 根据用户id找到购物车，然后创建订单
	 * @param orders
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Orders orders, List<Details> allDetails) throws Exception;
}
