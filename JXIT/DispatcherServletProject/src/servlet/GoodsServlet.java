package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceFactory;
 


import utils.General;
import vo.Goods;
import vo.Item;
import factories.ServiceFrontFactory;

/**
 * Servlet implementation class GoodsServlet
 */
@SuppressWarnings("serial")
@WebServlet("/pages/front/goods/GoodsServlet/*")
public class GoodsServlet extends DispatcherServlet 
{
	public String list()
	{
		super.handleSplit();
		try
		{
			System.out.println("[debug] currentPage: " + this.currentPage + ",lineSize: " + this.lineSize + ",column:" + this.column + ",keyWord:" + this.keyWord);
			
			
			Map<String, Object> map = ServiceFrontFactory.getIGoodsServiceFrontInstance().list(currentPage, lineSize, column, keyWord);
			List<Goods> allGoods = (List<Goods>)map.get("allGoods");
			Integer allGoodsCount = (Integer)map.get("allGoodsCount");
			List<Item> allItems = (List<Item>)map.get("allItems");
			Integer totalPages = (allGoodsCount + lineSize - 1) / lineSize;
			this.request.setAttribute("allGoods", allGoods);
			this.request.setAttribute("allGoodsCount", allGoodsCount);
			this.request.setAttribute("allItems", allItems);
			this.request.setAttribute("totalPages", totalPages);
			
			System.out.println("[debug] allGoods:" + allGoods);
			System.out.println("[debug] allGoodsCount:" + allGoodsCount);
			System.out.println("[debug] allItems:" + allItems); 
			return "/pages/front/goods/goods_list.jsp";
		}catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}
	@Override
	protected String getTitle()
	{
		return "商品";
	}

	@Override
	protected String getUploadFolderName()
	{
		return "goods";
	}

	@Override
	protected String getColumns()
	{
		return "商品名称:title|商品简介:note";
	}

	@Override
	protected String getColumn()
	{
		return "title";
	}

}
