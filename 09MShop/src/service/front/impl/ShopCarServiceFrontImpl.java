package service.front.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.front.IShopCarServiceFront;
import utils.CookieUtils;
import vo.Goods;
import vo.Member;
import vo.ShopCar;

public class ShopCarServiceFrontImpl implements IShopCarServiceFront
{
	public static final Integer cookieExpire = 3600 * 24 * 7;
	DatabaseConnection dbc = new DatabaseConnection();

	// @Override
	// public Map<String, Object> listCart(HttpServletRequest request) throws
	// Exception
	// {
	// try
	// {
	// Map<String, Object> map = new HashMap<String, Object>();
	//
	// Map<Integer, Integer> cart = this.loadCartFromCookie(request);
	// Set<Integer> ids = cart.keySet();
	//
	// List<Goods> allGoods =
	// DAOFactory.getIGoodsDAOInstance(conn).findAllByIds(ids);
	// map.put("allGoods", allGoods);
	// map.put("cart", cart);
	// return map;
	// } catch (Exception e)
	// {
	// throw e;
	// } finally
	// {
	// this.dbc.close();
	// }
	// }
	@Override
	public Map<String, Object> listCart(String mid) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			Map<Integer, Integer> cart = DAOFactory.getIShopCarDAOInstance(this.dbc.getConnection())
					.findAllByMember(mid);

			Set<Integer> gids = cart.keySet();
			List<Goods> allGoods = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByIds(
					gids);

			map.put("allGoods", allGoods);
			map.put("cart", cart);
			return map;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	//
	// @Override
	// public boolean addToCart(HttpServletRequest request,HttpServletResponse
	// response) throws Exception
	// {
	// try
	// {
	// Map<Integer, Integer> cart = this.loadCartFromCookie(request);
	// Integer gid = Integer.parseInt(request.getParameter("gid"));
	// Integer count = cart.get(gid);
	// if(count == null)
	// count = 1;
	// else
	// count++;
	// cart.remove(gid);
	// cart.put(gid, count);
	//
	// Set<Integer> ids = cart.keySet();
	// for(Integer id : ids)
	// {
	// CookieUtils.save(response, cookieExpire, "sc-" + String.valueOf(id),
	// String.valueOf(cart.get(id)));
	// }
	//
	// return true;
	// }catch(Exception e)
	// {
	// throw e;
	// }
	// finally
	// {
	// this.dbc.close();
	// }
	// }

	public boolean addToCart(ShopCar shopCar) throws Exception
	{
		try
		{
			if (DAOFactory.getIShopCarDAOInstance(this.dbc.getConnection()).findByMemberAndGoods(
					shopCar.getMember().getMid(), shopCar.getGoods().getGid()) != null)
			{
				// 购物车中已经存在该商品
				DAOFactory.getIShopCarDAOInstance(this.dbc.getConnection()).doUpdateAmount(
						shopCar.getMember().getMid(), shopCar.getGoods().getGid());
			} else
			{
				// 购物车中不存在该商品
				DAOFactory.getIShopCarDAOInstance(this.dbc.getConnection()).doCreate(shopCar);
			}
			return false;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	//
	// private Map<Integer, Integer> loadCartFromCookie(HttpServletRequest
	// request)
	// {
	// Map<Integer, Integer> cart = new HashMap<Integer, Integer>();
	// Map<String, String> allCookies = CookieUtils.load(request);
	//
	// Set<Map.Entry<String, String>> entries = allCookies.entrySet();
	// Iterator<Map.Entry<String, String>> iter = entries.iterator();
	// while (iter.hasNext())
	// {
	// Map.Entry<String, String> entry = iter.next();
	// String key = entry.getKey();
	// String value = entry.getValue();
	// if (key.startsWith("sc-"))
	// {
	// cart.put(Integer.parseInt(key.substring(3)), Integer.parseInt(value));
	// }
	// }
	//
	// return cart;
	// }

	//
	// @Override
	// public boolean removeFromCart(HttpServletRequest request,
	// HttpServletResponse response) throws Exception
	// {
	// try
	// {
	// String[] _ids = request.getParameter("ids").split("\\|");
	// for(String id : _ids )
	// {
	// CookieUtils.save(response, 0, "sc-" + id, "0");
	// }
	// return true;
	// }catch(Exception e)
	// {
	// throw e;
	// }
	// finally
	// {
	// this.dbc.close();
	// }
	// }

	public boolean removeFromCart(String mid, Set<Integer> gids) throws Exception
	{
		try
		{
			return DAOFactory.getIShopCarDAOInstance(this.dbc.getConnection()).doRemoveByMemberAndGoods(mid,
					gids);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	//
	// @Override
	// public boolean updateCart(HttpServletRequest request, HttpServletResponse
	// response) throws Exception
	// {
	// try
	// {
	// Enumeration<String> parameterNames = request.getParameterNames();
	// while(parameterNames.hasMoreElements())
	// {
	// String parameterName = parameterNames.nextElement();
	// Integer gid = Integer.parseInt(parameterName);
	// Integer amount = Integer.parseInt(request.getParameter(parameterName));
	// if(amount <= 0)
	// CookieUtils.save(response, 0, "sc-" + gid, String.valueOf(amount));
	// else
	// CookieUtils.save(response, cookieExpire, "sc-" + gid,
	// String.valueOf(amount));
	// }
	//
	// return false;
	// }
	// catch(Exception e)
	// {
	// throw e;
	// }
	// finally
	// {
	// this.dbc.close();
	// }
	// }

	public boolean updateCart(String mid, Map<Integer, Integer> cart) throws Exception
	{
		try
		{
			Set<Integer> goodsToBeRemoved = new HashSet<Integer>();
			Set<Integer> keys = cart.keySet();
			for (Integer key : keys)
			{
				if (cart.get(key) == 0)
				{
					// 如果商品的数量为0
					goodsToBeRemoved.add(key); 
				} else
				{
					ShopCar vo = new ShopCar();
					Member member = new Member();
					member.setMid(mid);
					vo.setMember(member);

					Goods goods = new Goods();
					goods.setGid(key);
					vo.setGoods(goods);

					vo.setAmount(cart.get(key));
					DAOFactory.getIShopCarDAOInstance(this.dbc.getConnection()).doUpdate(vo);
				}
			} 
			
			DAOFactory.getIShopCarDAOInstance(this.dbc.getConnection()).doRemoveByMemberAndGoods(mid,
					goodsToBeRemoved);

			return true;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

}
