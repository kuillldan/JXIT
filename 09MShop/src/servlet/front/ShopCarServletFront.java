package servlet.front;

import java.awt.Container;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factories.ServiceFrontFactory;
import utils.CONST;
import utils.General;
import vo.Goods;

/**
 * Servlet implementation class ShopCarServletFront
 */
@WebServlet("/pages/front/shopCart/ShopCarServletFront/*")
public class ShopCarServletFront extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShopCarServletFront()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		String path = CONST.pageError;
		String status = General.getStatus(request); 
		 
		if("add".equals(status))
		{
			path = this.add(request,response);
		}
		else if("list".equals(status))
		{
			path = this.list(request);
		}
		else if("delete".equals(status))
		{
			path = this.delete(request,response);
		}else if("update".equals(status))
		{
			path = this.update(request,response);
		}
		
		request.getRequestDispatcher(path).forward(request, response); 
	}
	
	public String update(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{ 
			ServiceFrontFactory.getIShopCarServiceFrontInstance().updateCart(request, response);
			String msg = "购物车更新成功";
			String url = CONST.pageCartServletListURL;
			
			return General.setMsgAndUrlInRequest(request, msg, url);
		}
		catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}
	
	
	public String delete(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			String msg = null;
			String url = null;
			ServiceFrontFactory.getIShopCarServiceFrontInstance().removeFromCart(request, response);
			msg = "购物车更新成功";
			url = CONST.pageCartServletListURL;
			
			return General.setMsgAndUrlInRequest(request, msg, url);
		}catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}
	
	public String add(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			String msg = null;
			String url = null;
			String referer = request.getHeader("referer"); 
			
			ServiceFrontFactory.getIShopCarServiceFrontInstance().addToCart(request, response);
			
			msg = "成功添加到购物车";
			url = CONST.pageGoodsServletFontURL + referer.substring(referer.lastIndexOf("/") + 1);
			return General.setMsgAndUrlInRequest(request, msg, url);
		}catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}
	
	public String list(HttpServletRequest request)
	{ 
		try
		{  
			Map<String, Object> map = ServiceFrontFactory.getIShopCarServiceFrontInstance().listCart(request);
			List<Goods> allGoods = (List<Goods>)map.get("allGoods"); 
			Map<Integer, Integer> cart = (Map<Integer, Integer>)map.get("cart");
			request.setAttribute("allGoods", allGoods);
			request.setAttribute("cart", cart);
			return CONST.pageCartListJSP;
		}
		catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}

}
