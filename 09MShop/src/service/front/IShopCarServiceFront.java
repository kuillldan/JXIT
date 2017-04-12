package service.front;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Goods;
import vo.ShopCar;

public interface IShopCarServiceFront
{
	/**
	 * 列出用户购物车中的所有商品
	 * @param mid
	 * @return allGoods(List<Goods>)/cart(Map<Integer,Integer>)
	 * @throws Exception
	 */
	public Map<String, Object> listCart(String mid) throws Exception;
	/**
	 * 向购物车中增加一个商品,如果该商品已经在购物车中存在，则数量+1
	 * @param shopCar
	 * @return
	 * @throws Exception
	 */
	public boolean addToCart(ShopCar shopCar) throws Exception;
	/**
	 * 将特定商品从用户购物车中移除
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean removeFromCart(String mid, Set<Integer> gids) throws Exception;
	/**
	 * 更新用户购物车信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean updateCart(String mid, Map<Integer,Integer> cart)throws Exception;
}
