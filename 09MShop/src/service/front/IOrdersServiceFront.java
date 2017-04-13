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
	
	/**
	 * 查询一个用户的所有订单信息
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> listByMember(String mid,Integer currentPage, Integer  lineSize) throws Exception;
	
	/**
	 * 查询某个用户的指定的订单信息以及订单详情信息
	 * @param mid
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public Orders show(String mid, Integer oid) throws Exception;
}
