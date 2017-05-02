
package service.front.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbc.DatabaseConnection;
import factories.DAOFactory;
import service.front.IEmployeeServiceFront;
import utils.General;
import vo.Dept;
import vo.Employee;
import vo.Jobs;
import vo.Level;

public class EmployeeServiceFrontImpl implements IEmployeeServiceFront
{
	DatabaseConnection dbc = new DatabaseConnection();
	
	@Override
	public Map<String, Object> insertPre() throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Dept> allDepts = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll();
			List<Level> allLevels = DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findAll();
			List<Jobs> allJobs = DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findAll();
			
			map.put("allDepts", allDepts);
			map.put("allLevels", allLevels);
			map.put("allJobs", allJobs);
			
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
	public Map<String, Object> updatePre(Integer eid) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Dept> allDepts = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll();
			List<Level> allLevels = DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findAll();
			List<Jobs> allJobs = DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findAll();
			
			map.put("allDepts", allDepts);
			map.put("allLevels", allLevels);
			map.put("allJobs", allJobs);
			Employee employee = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findById(eid);
			map.put("employee", employee);
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
	public boolean insert(Employee employee) throws Exception
	{
		try
		{
			employee.setOutdate(null);
			Dept dept = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(employee.getDept().getDid());
			Jobs jobs = DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findById(employee.getJobs().getJid());
//			Level level = DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findById(employee.getLevel().getLevid());
			
			employee.setDname(dept.getDname()); 
			employee.setJob(jobs.getTitle());
			
			return DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doCreate(employee);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Employee employee) throws Exception
	{
		try
		{
			Dept dept = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(employee.getDept().getDid());
			Jobs jobs = DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findById(employee.getJobs().getJid());
//			Level level = DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findById(employee.getLevel().getLevid());
			
			employee.setDname(dept.getDname()); 
			employee.setJob(jobs.getTitle());
			
			return DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doUpdate(employee);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public boolean updateOut(List<Integer> ids) throws Exception
	{
		try
		{
			return DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doUpdateStatus(ids, General.getCurrentSqlDate());
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> list(Integer currentPage, Integer pageSize, String column, String keyWord)
			throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Employee> allEmployees = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, pageSize, column, keyWord);
			Integer allEmployeesCount = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord);
			map.put("allEmployees", allEmployees);
			map.put("allEmployeesCount", allEmployeesCount); 
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
	public Map<String, Object> listByStatus(Integer currentPage, Integer pageSize, String column, String keyWord,
			Integer status) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Employee> allEmployees = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findAllSplitByStatus(currentPage, pageSize, column, keyWord, status);
			Integer allEmployeesCount = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).getAllCountByStatus(column, keyWord, status);
			map.put("allEmployees", allEmployees);
			map.put("allEmployeesCount", allEmployeesCount);
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
