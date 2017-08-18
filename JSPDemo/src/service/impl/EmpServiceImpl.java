package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dbc.DatabaseConnection;
import factory.DAOFactory;
import service.IEmpService;
import vo.Emp;

public class EmpServiceImpl implements IEmpService
{
	Connection conn = DatabaseConnection.getConnection();
	
	@Override
	public boolean insert(Emp emp) throws SQLException
	{
		try
		{
			return DAOFactory.getEmpDAOInstance(this.conn).doCreate(emp);
		}catch(Exception e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(this.conn);
		}
	}

	@Override
	public boolean update(Emp emp) throws SQLException
	{
		try
		{
			return DAOFactory.getEmpDAOInstance(conn).doUpdate(emp);
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(this.conn);
		}
	}

	@Override
	public Emp updatePre(Integer empno) throws SQLException
	{
		try
		{
			return DAOFactory.getEmpDAOInstance(conn).findById(empno);
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(this.conn);
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws SQLException
	{
		try
		{
			return DAOFactory.getEmpDAOInstance(conn).doRemoveBatch(ids);
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(this.conn);
		}
	}

	@Override
	public List<Emp> list() throws SQLException
	{
		try
		{
			return DAOFactory.getEmpDAOInstance(conn).findAll();
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(this.conn);
		}
	}

	@Override
	public Map<String,Object> listSplit(Integer currentPage, Integer lineSize) throws SQLException
	{
		try
		{
			List<Emp> allEmps = new ArrayList<Emp>();
			Integer allCount = null;
			Map<String, Object> retVal = new HashMap<String, Object>();
			allEmps = DAOFactory.getEmpDAOInstance(conn).findAllSplit(currentPage, lineSize);
			allCount = DAOFactory.getEmpDAOInstance(conn).getAllCount();
			retVal.put("allEmps", allEmps);
			retVal.put("allCount", allCount);	
			return retVal;
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(this.conn);
		}
	}

	@Override
	public Emp show(Integer empno) throws SQLException
	{
		try
		{
			return DAOFactory.getEmpDAOInstance(conn).findById(empno);
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			DatabaseConnection.close(this.conn);
		}
	}

}
