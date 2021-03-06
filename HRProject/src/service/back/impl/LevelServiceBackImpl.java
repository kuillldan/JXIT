package service.back.impl;

import java.util.List;
import java.util.Set;

import javax.servlet.jsp.tagext.TryCatchFinally;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.back.ILevelServiceBack;
import vo.Level;

public class LevelServiceBackImpl implements ILevelServiceBack
{
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean insert(Level level) throws Exception
	{
		try
		{
			if(DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findByTitle(level.getTitle()))
			{
				return false;
			}
			else
			{
				return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).doCreate(level);
			}
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception
	{
		try
		{
			return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public List<Level> list() throws Exception
	{
		try
		{
			return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Level level) throws Exception
	{
		try
		{
			if(DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findByTitleAndId(level.getTitle(), level.getLevid()))
			{ 
				return false;
			}
			else
			{
				return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).doUpdate(level);
			}
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

}
