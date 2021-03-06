package service.back.impl;

import java.util.List;
import java.util.Set;

import javax.servlet.jsp.tagext.TryCatchFinally;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.back.IJobsServiceBack;
import vo.Jobs;

public class JobsServiceBackImpl implements IJobsServiceBack
{
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public boolean insert(Jobs jobs) throws Exception
	{
		try
		{
			if (DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findByTitle(jobs.getTitle()))
				return false;
			else
			{
				return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).doCreate(jobs);
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
			if (ids.size() <= 0)
				return false;

			return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Jobs jobs) throws Exception
	{
		try
		{
			if (DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findByTitleAndId(jobs.getTitle(),
					jobs.getJid()))
				return false;
			else
			{
				return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).doUpdate(jobs);
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
	public List<Jobs> list() throws Exception
	{
		try
		{
			return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

}
