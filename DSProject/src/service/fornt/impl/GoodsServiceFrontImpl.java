package service.fornt.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbc.DatabaseConnection;
import factories.DAOFactory; 
import service.fornt.IGoodsServiceFront;
import utils.CONST;
import vo.Goods;
import vo.Item;

public class GoodsServiceFrontImpl implements  IGoodsServiceFront
{
	DatabaseConnection dbc = new DatabaseConnection();
	 

	@Override
	public Map<String, Object> list(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Goods> allGoods = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllSplitByStatus(currentPage, lineSize, column, keyWord, 1);
			Integer allGoodsCount = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).getAllCountByStatus(column, keyWord, 1);
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
 
	
}
