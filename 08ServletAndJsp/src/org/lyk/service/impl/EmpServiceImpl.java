package org.lyk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.lyk.dbc.DatabaseConnection;
import org.lyk.factory.DAOFactory;
import org.lyk.service.IEmpService;
import org.lyk.vo.Emp;

public class EmpServiceImpl implements IEmpService
{

	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public boolean insert(Emp emp) throws Exception
	{
		try
		{
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doCreate(emp);
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
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public Emp findById(Integer empno) throws Exception
	{
		try
		{
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findById(empno);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> listBySplit(Integer currentPage, Integer lineSize, String column,
			String keyWord) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Emp> allEmps = DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAllSplit(
					currentPage, lineSize, column, keyWord);
			map.put("allEmps", allEmps);
			Integer allRecords = DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).getAllCount(column,
					keyWord);
			map.put("allRecords", allRecords);

	 

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
	public boolean update(Emp emp) throws Exception
	{
		try
		{
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doUpdate(emp);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public List<Emp> listAll() throws Exception
	{
		try
		{
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public Emp show(Integer empno) throws Exception
	{
		try
		{
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findByIdDetails(empno);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> insertPre() throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allDepts", DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll());
			map.put("allEmps", DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAll());
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
	public Map<String, Object> updatePre(Integer empno) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allEmps", DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAll());
			map.put("allDepts", DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll());
			map.put("emp", DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findByIdDetails(empno));
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
	public Map<String, Object> listDetails(String column, String keyWord, Integer currentPage,
			Integer lineSize) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allEmps", DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAllSplitDetails(column, keyWord, currentPage, lineSize));
			map.put("allRecords", DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
			return map;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		} 
	}

}
