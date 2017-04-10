package org.lyk.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import org.lyk.dbc.DatabaseConnection;
import org.lyk.factory.DAOFactory;
import org.lyk.service.IDeptService;
import org.lyk.utils.General;
import org.lyk.vo.Dept;

public class DeptServiceImpl implements IDeptService
{
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public boolean insert(Dept vo) throws Exception
	{
		try
		{
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doCreate(vo);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Dept vo) throws Exception
	{
		try
		{
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdate(vo);
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
		Connection conn = this.dbc.getConnection();
		try
		{
			boolean flag = true;
			conn.setAutoCommit(false);
			
			//查询所有员工对应的photo信息
			List<String> allPhotos = DAOFactory.getIEmpDAOInstance(conn).findAllPhotosByDeptno(ids);
			General.removePhotos(allPhotos);
			
			//移除所有员工信息
			for (Integer deptno : ids)
			{
				DAOFactory.getIEmpDAOInstance(conn).doRemoveByDeptno(deptno);
			}

			flag = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
			conn.commit();
			return flag;
		} catch (Exception e)
		{
			conn.rollback();
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public List<Dept> list() throws Exception
	{
		try
		{
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public Dept updatePre(Integer id) throws Exception
	{
		try
		{
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(id);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public List<Dept> listAllDetails() throws Exception
	{
		try
		{
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAllWithStat();
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public Dept show(Integer deptno, String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception
	{
		Dept dept = null;
		dept  = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findByIdWithStat(deptno);
		dept.setAllEmps(DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAllSplitDetailsByDeptno(deptno, column, keyWord, currentPage, lineSize));
		return dept;
	}

}
