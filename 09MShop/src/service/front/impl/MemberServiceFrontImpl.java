package service.front.impl;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.front.IMemberServiceFront;
import utils.CONST;
import vo.Member;

public class MemberServiceFrontImpl implements IMemberServiceFront
{
	DatabaseConnection dbc = new DatabaseConnection();
	
	@Override
	public boolean regist(Member vo) throws Exception
	{
		try
		{
			if(null != DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById(vo.getMid()))
				return false;
			else
			{
				return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doCreate(vo);
			}
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
	public boolean active(String mid, String code) throws Exception
	{
		try
		{
			if(DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findByCode(mid, code))
			{
				return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doUpdateStatus(mid, CONST.MemberStatus.ACTIVED.ordinal());
			}
			else
			{
				return false;
			}
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
	public boolean login(Member vo) throws Exception
	{
		try
		{
			return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findLogin(vo); 
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
	public Member updatePre(String mid) throws Exception
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

	@Override
	public boolean update(Member member) throws Exception
	{
		try
		{ 
			return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doUpdateMember(member);
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
	public Member findByMid(String mid) throws Exception
	{
		return this.updatePre(mid);
	}

}
