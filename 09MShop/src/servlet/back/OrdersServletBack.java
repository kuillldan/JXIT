package servlet.back;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.jms.TopicConnectionFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

import factories.DAOFactory;
import factories.ServiceBackFactory;
import factories.ServiceFrontFactory;
import utils.CONST;
import utils.General;
import vo.Details;
import vo.Orders;



/**
 * Servlet implementation class OrdersServletBack
 */
@WebServlet("/pages/back/admin/orders/OrdersServletBack/*")
public class OrdersServletBack extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrdersServletBack()
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
		// TODO Auto-generated method stub
		String path = CONST.pageError;
		String status = General.getStatus(request);
		
		if("list".equals(status))
		{
			path = this.list(request);
		}else if("show".equals(status))
		{
			path = this.show(request);
		}
		
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
	
	private String list(HttpServletRequest request)
	{
		try
		{
			Integer currentPage = 1;
			Integer lineSize = 5;
			String column = "mid";
			String keyWord = "";
			String columns = "会员名:mid|收件人:name|电话:phone|地址:address";
			
			try
			{
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
			}catch(Exception e)
			{}
			try
			{
				lineSize = Integer.parseInt(request.getParameter("lineSize"));
			}catch(Exception e)
			{}
			
			if(!utils.StringUtils.isEmpty(request.getParameter("columnName")))
			{
				column = request.getParameter("columnName");
			}
			if(!utils.StringUtils.isEmpty(request.getParameter("keyWord")))
			{
				keyWord = request.getParameter("keyWord");
			}
			if(!utils.StringUtils.isEmpty(request.getParameter("columns"))) 
			{
				columns = request.getParameter("columns");
			}
			
			
			
			
			Map<String, Object> map = ServiceBackFactory.getIOrdersServiceBackInstance().list(currentPage, lineSize, column, keyWord);
			List<Orders> allOrders = (List<Orders>)map.get("allOrders");
			Integer allOrdersCount = (Integer)map.get("allOrdersCount");
			request.setAttribute("allOrders", allOrders);
			request.setAttribute("allOrdersCount", allOrdersCount); 
			request.setAttribute("totalPages", (allOrdersCount + lineSize -1)/lineSize);
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("column", column);
			request.setAttribute("keyWord", keyWord);
			request.setAttribute("columns", columns);
			
			return "/pages/back/admin/orders/orders_list.jsp";
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
			Orders orders = ServiceBackFactory.getIOrdersServiceBackInstance().show(oid);
			List<Details> allDetails = orders.getAllDetails();
			request.setAttribute("orders", orders);
			request.setAttribute("allDetails", allDetails);
			return "/pages/back/admin/orders/order_details.jsp";
		}catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}

}
