package servlet.front;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factories.ServiceBackFactory;
import factories.ServiceFrontFactory;
import utils.CONST;
import utils.General;
import utils.StringUtils;
import vo.Goods;
import vo.Item;

/**
 * Servlet implementation class GoodsServletFront
 */
@WebServlet("/pages/front/goods/GoodsServletFront/*")
public class GoodsServletFront extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoodsServletFront()
	{
		super(); 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{ 
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
		if("list".equals(status))
		{
			path = this.list(request);
		}else if("show".equals(status))
		{
			path = this.show(request);
		}
		
		
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	public String show(HttpServletRequest request)
	{
		try
		{  
			Integer gid = Integer.parseInt(request.getParameter("gid"));
			
			Goods goods = ServiceFrontFactory.getIGoodsServiceFrontInstance().show(gid);
			request.setAttribute("goods", goods);
			
			return CONST.pageGoodsShowJSP;
		}
		catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}

	public String list(HttpServletRequest request)
	{
		String columns = "商品名:title|发布者:aid";
		Integer currentPage = 1;
		Integer lineSize = 5;
		String column = "title";
		String keyWord = "";

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
		if (!StringUtils.isEmpty(request.getParameter("column")))
		{
			column = request.getParameter("column");
		}
		if (!StringUtils.isEmpty(request.getParameter("keyWord")))
		{
			keyWord = request.getParameter("keyWord");
		}

		try
		{
			String iid = request.getParameter("iid"); 
			Map<String, Object> map = null;
			if (!StringUtils.isEmpty(iid))
			{ 
				map = ServiceFrontFactory.getIGoodsServiceFrontInstance().listByItem(Integer.parseInt(iid), currentPage, lineSize, column, keyWord);

				request.setAttribute("parameterKey", "iid");
				request.setAttribute("parameterValue", Integer.parseInt(iid));
			} else
			{
				map = ServiceFrontFactory.getIGoodsServiceFrontInstance().list(currentPage, lineSize, column, keyWord);
			}

			List<Goods> allGoods = (List<Goods>) map.get("allGoods");
			Integer allCount = (Integer) map.get("allGoodsCount");
			List<Item> allItems = (List<Item>)map.get("allItems");
			
			Integer toalPages = (allCount + lineSize - 1) / lineSize;
			request.setAttribute("allGoods", allGoods);
			request.setAttribute("allCount", allCount);
			request.setAttribute("allItems", allItems);

			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("columns", columns);
			request.setAttribute("column", column);
			request.setAttribute("keyWord", keyWord);
			request.setAttribute("totalPages", toalPages);

			return CONST.pageGoodsFrontListJSP;
		} catch (Exception e)
		{
			return General.setSystemError(e);
		} 
	}
}
