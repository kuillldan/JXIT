package bitool.service.impl;

import java.sql.Connection;

import bitool.dbc.BI_DatabaseConnection;
import bitool.dao.IOpenOffManagement;
import bitool.exception.RecordUpdatedException;
import bitool.factory.DAOFactory;
import bitool.service.IOpenOffManagementService;
import bitool.vo.OpenOffManagement;

public class OpenOffManagementServiceImpl implements IOpenOffManagementService
{
	BI_DatabaseConnection dbc = new BI_DatabaseConnection();

	@Override
	public OpenOffManagement findOpenOffManagement() throws Exception
	{
		try
		{
			return DAOFactory.getOpenOffManagementDAOInstance(this.dbc.getConnection()).find();
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public boolean updateTime(OpenOffManagement vo, long modtime) throws RecordUpdatedException, Exception
	{
		try
		{
			Connection conn = this.dbc.getConnection();
			conn.setAutoCommit(false);
			this.check(conn, modtime);
			boolean retVal = DAOFactory.getOpenOffManagementDAOInstance(conn).updateTime(vo);
			conn.commit();
			return retVal;
		} catch (RecordUpdatedException e)
		{
			throw e;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

  

	@Override
	public boolean updateMode(String mode, long modtime) throws RecordUpdatedException, Exception
	{
		try
		{
			Connection conn = this.dbc.getConnection();
			conn.setAutoCommit(false);
			this.check(conn, modtime); 
			boolean retVal = DAOFactory.getOpenOffManagementDAOInstance(conn).updateMode(mode);
			conn.commit();
			return retVal;
		}
		catch(RecordUpdatedException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	private void check(Connection conn, long modtime) throws RecordUpdatedException, Exception
	{
		OpenOffManagement latestStatus = DAOFactory.getOpenOffManagementDAOInstance(conn).find();

		if (latestStatus.getModtime().getTime() != modtime)
		{
			throw new RecordUpdatedException();
		}
	}
}
