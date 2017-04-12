package DAO;

import java.util.Map;
import java.util.Set;

import vo.ShopCar;

public interface IShopCarDAO extends IDAO<Integer, ShopCar>
{
	/**
	 * 像用户购物车中增加一个商品，如果该商品已经在购物车中，则总数增加1
	 * @param mid
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateAmount(String mid, Integer gid) throws Exception;
	/**
	 * 根据用户ID以及商品ID判断该商品是否已经添加在购物车中了
	 * @param mid
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public ShopCar findByMemberAndGoods(String mid, Integer gid) throws Exception;
	
	/**
	 * 将用户购物车清空
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public boolean doRemoveByMember(String mid) throws Exception;
	
	/**
	 * 批量增加商品到用户购物车中
	 * @param mid
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public boolean doCreateBatch(String mid, Set<Integer> gid) throws Exception;
	
	/**
	 * 批量将商品从用户购物车总移除
	 * @param mid
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public boolean doRemoveByMemberAndGoods(String mid, Set<Integer> gid) throws Exception;
	
	/**
	 * 将用户购物车中所有商品列出
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, Integer> findAllByMember(String mid) throws Exception;
}
