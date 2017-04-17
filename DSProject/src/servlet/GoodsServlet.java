package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Goods;

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
		System.out.println("[debug] " + this.goods);
		return "/pages/front/goods/insertXX.jsp";
	}

	public String list()
	{
		System.out.println("[debug] " + this.goods);
		return "/pages/front/goods/goods_list.jsp";
	}
}
