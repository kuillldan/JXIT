package servlet.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import factories.ServiceBackFactory;
import factories.ServiceFrontFactory;
import utils.CONST;
import utils.General;
import utils.StringUtils;
import vo.Details;
import vo.Goods;
import vo.Member;
import vo.Orders;

/**
 * Servlet implementation class OrdersServletFront
 */
@WebServlet("/pages/front/shopCart/OrdersServletFront/*")
public class OrdersServletFront extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrdersServletFront()
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
		if ("insert".equals(status))
		{
			path = this.insert(request);
		} else if ("list".equals(status))
		{
			path = this.list(request);
		} else if ("show".equals(status))
		{
			path = this.show(request);
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	private String list(HttpServletRequest request)
	{
		try
		{
			HttpSession session = request.getSession();
			String mid = (String) session.getAttribute("mid");
			Integer currentPage = 1;
			Integer lineSize = 5;
			try
			{
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			} catch (Exception e)
			{
			}
			try
			{
				lineSize = Integer.parseInt(request.getParameter("lineSize"));
			} catch (Exception e)
			{
			}

			Map<String, Object> map = ServiceFrontFactory.getIOrdersServiceFrontInstance().listByMember(mid,
					currentPage, lineSize);
			List<Orders> allOrders = (List<Orders>) map.get("allOrders");
			Integer allOrdersCount = (Integer) map.get("allOrdersCount");
			request.setAttribute("allOrders", allOrders);
			request.setAttribute("allOrdersCount", allOrdersCount);

			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("totalPages", (allOrdersCount + lineSize - 1) / lineSize);

			return "/pages/front/orders/orders_list.jsp";

		} catch (Exception e)
		{
			return General.setSystemError(e);
		}
	}

	private String show(HttpServletRequest request)
	{
		
		try
		{
			Integer oid = Integer.parseInt(request.getParameter("oid"));
			String mid = (String)(request.getSession().getAttribute("mid"));
			Orders orders = ServiceFrontFactory.getIOrdersServiceFrontInstance().show(mid, oid);
			List<Details> allDetails = orders.getAllDetails();
			request.setAttribute("orders", orders);
			request.setAttribute("allDetails", allDetails);
			return "/pages/front/orders/order_details.jsp";
		}catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}

	private String insert(HttpServletRequest request)
	{
		try
		{
			String mid = (String) (request.getSession().getAttribute("mid"));
			Member member = ServiceFrontFactory.getIMemberServiceFrontInstance().findByMid(mid);
			// 验证用户的姓名、电话、地址信息
			String msg = null;
			String url = "/pages/front/member/MemberInfoServletFront/updatePre?mid=" + mid;
			if (StringUtils.isEmpty(member.getName()))
			{
				msg = "收货人姓名不能为空，请补充个人信息";
				return General.setMsgAndUrlInRequest(request, msg, url);
			}
			if (StringUtils.isEmpty(member.getPhone()))
			{
				msg = "收货人电话不能为空，请补充个人信息";
				return General.setMsgAndUrlInRequest(request, msg, url);
			}
			if (StringUtils.isEmpty(member.getAddress()))
			{
				msg = "收货人地址不能为空，请补充个人信息";
				return General.setMsgAndUrlInRequest(request, msg, url);
			}
			Map<String, Object> cartInfo = ServiceFrontFactory.getIShopCarServiceFrontInstance()
					.listCart(mid);
			Map<Integer, Integer> cart = (Map<Integer, Integer>) cartInfo.get("cart");
			if (cart.size() <= 0)
			{
				msg = "购物车为空，请先添加商品";
				url = "/pages/front/goods/GoodsServletFront/list";
				return General.setMsgAndUrlInRequest(request, msg, url);
			}

			Set<Integer> allGids = cart.keySet();
			List<Details> allDetails = new ArrayList<Details>();
			Double pay = 0.0;
			for (Integer gid : allGids)
			{
				Details details = new Details();
				Integer amountInCart = cart.get(gid);
				Goods goods = ServiceFrontFactory.getIGoodsServiceFrontInstance().show(gid);
				if (goods.getAmount() < amountInCart)
				{
					msg = "商品" + goods.getTitle() + "库存量不足，请更新购物车";
					url = "/pages/front/shopCart/ShopCarServletFront/list";
					return General.setMsgAndUrlInRequest(request, msg, url);
				} else
				{
					pay += goods.getPrice();
					details.setGoods(goods);
					details.setTitle(goods.getTitle());
					details.setPrice(goods.getPrice());
					details.setAmount(amountInCart);
					allDetails.add(details);

					goods.setAmount(goods.getAmount() - amountInCart);
					cart.put(gid, 0);
				}
			}
			Orders orders = new Orders();
			orders.setMid(mid);
			orders.setName(member.getName());
			orders.setPhone(member.getPhone());
			orders.setAddress(member.getAddress());
			orders.setCredate(new Date());
			orders.setPay(pay);

			if (ServiceFrontFactory.getIOrdersServiceFrontInstance().insert(orders, allDetails))
			{
				// 订单生成成功
				// 4 更新库存信息
				// 5 更新购物车信息

				for (Details details : allDetails)
				{
					Goods goods = details.getGoods();
					ServiceBackFactory.getIGoodsServiceBackInstance().update(goods);
				}

				if (ServiceFrontFactory.getIShopCarServiceFrontInstance().updateCart(mid, cart))
				{
					msg = "订单生成成功";
				} else
				{
					msg = "订单生成失败";
				}

				url = "/pages/front/shopCart/ShopCarServletFront/list";
				return General.setMsgAndUrlInRequest(request, msg, url);
			} else
			{
				// 订单生成失败
				msg = "订单生成失败，请稍后再试";
				url = "/pages/front/shopCart/ShopCarServletFront/list";
				return General.setMsgAndUrlInRequest(request, msg, url);
			}

		} catch (Exception e)
		{
			return General.setSystemError(e);
		}
	}

}
