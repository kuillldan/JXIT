package service.front;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Goods;

public interface IShopCarServiceFront
{
	public Map<String, Object> listCart(HttpServletRequest request) throws Exception;
	public boolean addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public boolean removeFromCart(HttpServletRequest request,HttpServletResponse response) throws Exception;
	public boolean updateCart(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
