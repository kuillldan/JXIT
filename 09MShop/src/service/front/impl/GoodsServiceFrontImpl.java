package service.front.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.front.IGoodsServiceFront;
import utils.CONST;
import vo.Goods;
import vo.Item;

public class GoodsServiceFrontImpl implements IGoodsServiceFront
{
	DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public Map<String, Object> listByItem(Integer iid, Integer currentPage, Integer lineSize, String column,
			String keyWord) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Goods> allGoods = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByItem(iid, currentPage, lineSize, column, keyWord, CONST.GoodsStatus.UP.ordinal());
			Integer allGoodsCount = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).getAllCountByItem(iid, column, keyWord, CONST.GoodsStatus.UP.ordinal());
			List<Item> allItems = DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll();
			map.put("allGoods", allGoods);
			map.put("allGoodsCount", allGoodsCount);
			map.put("allItems", allItems);
			return map;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			this.dbc.close();
		} 
	}

	@Override
	public Map<String, Object> list(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Goods> allGoods = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllSplitByStatus(currentPage, lineSize, column, keyWord, CONST.GoodsStatus.UP.ordinal());
			Integer allGoodsCount = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).getAllCountByStatus(column, keyWord, CONST.GoodsStatus.UP.ordinal());
			List<Item> allItems = DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll();
			map.put("allGoods", allGoods);
			map.put("allGoodsCount", allGoodsCount);
			map.put("allItems", allItems);
			return map;
		}catch(Exception e)
		{
			throw e;
		}
		finally
		{
			this.dbc.close();
		} 
	}

	@Override
	public Goods show(Integer gid) throws Exception
	{
		try
		{
			Goods goods = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findById(gid);
			if(goods != null)
			{
				Item item = DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findById(goods.getItem().getIid());
				goods.setItem(item);
				
				DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateBow(gid);
			}
			return goods;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			this.dbc.close();
		}
	}
	
}
