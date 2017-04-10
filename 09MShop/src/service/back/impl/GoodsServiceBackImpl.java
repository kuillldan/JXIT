package service.back.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.back.IGoodsServiceBack;
import utils.CONST;
import vo.Goods;
import vo.Item;

public class GoodsServiceBackImpl implements IGoodsServiceBack
{
	DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public Map<String, Object> insertPre() throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Item> allItems = DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll();
			map.put("allItems", allItems);
			return map;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public boolean insert(Goods vo) throws Exception
	{
		try
		{
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doCreate(vo);
		} catch (Exception e)
		{
			throw e;
		} finally
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
			List<Goods> allGoods = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord);
			Integer allCount = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord);
			
			for(Goods good : allGoods)
			{
				Item item = DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findById(good.getItem().getIid());
				good.getItem().setTitle(item.getTitle());
			}
			
			map.put("allGoods", allGoods);
			map.put("allCount", allCount);
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
	public Map<String, Object> listByStatus(Integer currentPage, Integer lineSize, String column,
			String keyWord, Integer status) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Goods> allGoods = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllSplitByStatus(currentPage, lineSize, column, keyWord,status);
			Integer allCount = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).getAllCountByStatus(column, keyWord,status);
			
			for(Goods good : allGoods)
			{
				Item item = DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findById(good.getItem().getIid());
				good.getItem().setTitle(item.getTitle());
			}
			
			map.put("allGoods", allGoods);
			map.put("allCount", allCount);
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
	public boolean updateUp(Set<Integer> ids) throws Exception
	{
		try
		{
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateStatus(ids, CONST.GoodsStatus.UP.ordinal());
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
	public boolean updateDown(Set<Integer> ids) throws Exception
	{
		try
		{
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateStatus(ids, CONST.GoodsStatus.DOWN.ordinal());
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
	public boolean updateDelete(Set<Integer> ids) throws Exception
	{
		try
		{
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateStatus(ids, CONST.GoodsStatus.DELETED.ordinal());
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
	public Map<String, Object> updatePre(Integer gid) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allItems", DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
			map.put("goods", DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findById(gid));
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
	public boolean update(Goods vo) throws Exception
	{
		try
		{
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdate(vo);
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
	public boolean deleteAll(Set<Integer> ids) throws Exception
	{
		try
		{
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
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