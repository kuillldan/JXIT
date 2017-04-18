package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factories.ServiceFrontFactory;
import vo.Goods;
import vo.Item;

@SuppressWarnings("serial")
@WebServlet("/pages/front/goods/GoodsServlet/*")
public class GoodsServlet extends AbstractServlet
{
	private String insertValidation = "goods.aid|goods.pubdate|goods.amount|goods.item.otherInfo.locs";
	private Goods goods = new Goods();

	public Goods getGoods()
	{
		return goods;
	}

	public void setGoods(Goods goods)
	{
		this.goods = goods;
	}

	public String insert()
	{
		return "/pages/front/goods/insertXX.jsp";
	}

	public String list()
	{
		try
		{
			super.handleSplit();
			System.out.println("[debug] currentPage:" + currentPage + ",lineSize:" + lineSize + ",column:" + column + ",keyWord:" + keyWord);
			Map<String, Object> map = ServiceFrontFactory.getIGoodsServiceFrontInstance().list(currentPage, lineSize,
					column, keyWord);
			List<Goods> allGoods = (List<Goods>) map.get("allGoods");
			List<Item> allItems = (List<Item>) map.get("allItems");
			Integer allGoodsCount = (Integer) map.get("allGoodsCount");
			Integer totalPages = (allGoodsCount + lineSize - 1) / lineSize;
			
			request.setAttribute("allGoods", allGoods);
			request.setAttribute("allItems", allItems);
			request.setAttribute("allGoodsCount", allGoodsCount);
			request.setAttribute("totalPages", totalPages);
			System.out.println("[debug] allGoodsCount:" + allGoodsCount);
			System.out.println("[debug] totalPages:" + totalPages);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "/pages/front/goods/goods_list.jsp";
	}

	@Override
	protected String getUploadFolder()
	{
		return "goods";
	}

	@Override
	protected String getColumns()
	{
		return "商品名称:title|商品描述:note";
	}

	@Override
	protected String getColumn()
	{
		return "title";
	}
}
