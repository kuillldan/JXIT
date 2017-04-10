package service.back.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.back.IMemberServiceBack;
import utils.CONST;
import vo.Member;

public class MemberServiceBackImpl implements IMemberServiceBack
{
	DatabaseConnection dbc = new DatabaseConnection();
	
	@Override
	public Map<String, Object> list(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Member> allMembers = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord);
			Integer allCount = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord);
			map.put("allMembers", allMembers);
			map.put("allCount", allCount);
			
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
	public Map<String, Object> listByStatus(Integer currentPage, Integer lineSize, String column,
			String keyWord, Integer status) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Member> allMembers = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findAllSplitByStatus(currentPage, lineSize, column, keyWord,status);
			Integer allCount = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).getAllCountByStatus(column, keyWord,status);
			map.put("allMembers", allMembers);
			map.put("allCount", allCount);
			
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
	public boolean activeMembers(Set<String> ids) throws Exception
	{
		try
		{
			return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doUpdateStatus(ids, CONST.MemberStatus.ACTIVED.ordinal());
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
	public boolean disableMembers(Set<String> ids) throws Exception
	{
		try
		{
			return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doUpdateStatus(ids, CONST.MemberStatus.LOCKED.ordinal());
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
	public Member show(String mid) throws Exception
	{
		try
		{
			return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById(mid);
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
