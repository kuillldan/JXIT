package org.lyk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.lyk.dao.AbstractDAO;
import org.lyk.dao.IEmpDAO;
import org.lyk.vo.Dept;
import org.lyk.vo.Emp;

public class EmpDAOImpl extends AbstractDAO implements IEmpDAO
{
	private PreparedStatement ps;

	public EmpDAOImpl(Connection conn)
	{
		super(conn);
	}

	@Override
	public boolean doCreate(Emp vo) throws Exception
	{
		String sql = " INSERT INTO Emp(empno,ename,job,mgr,hiredate,sal,comm,deptno,photo,note) VALUES(?,?,?,?,?,?,?,?,?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, vo.getEmpno());
		this.ps.setString(2, vo.getEname());
		this.ps.setString(3, vo.getJob());
		if (vo.getMgr() == null)
		{
			this.ps.setNull(4, Types.NULL);
		} else
		{
			this.ps.setInt(4, vo.getMgr().getEmpno());
		}
		this.ps.setDate(5, new java.sql.Date(vo.getHiredate().getTime()));
		this.ps.setInt(6, vo.getSal());
		this.ps.setInt(7, vo.getComm());
		if (vo.getDept() == null)
		{
			this.ps.setNull(8, Types.NULL);
		} else
		{
			this.ps.setInt(8, vo.getDept().getDeptno());
		}
		
		this.ps.setString(9, vo.getPhoto());
		this.ps.setString(10, vo.getNote());
		
		
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(Emp vo) throws Exception
	{
		String sql = " UPDATE Emp SET ename=?,job=?,mgr=?,hiredate=?,sal=?,comm=?,deptno=?,photo=?,note=? WHERE empno=? ";
		this.ps = this.conn.prepareStatement(sql);

		this.ps.setString(1, vo.getEname());
		this.ps.setString(2, vo.getJob());
		if (vo.getMgr() != null)
		{
			this.ps.setInt(3, vo.getMgr().getEmpno());
		} else
		{
			this.ps.setNull(3, Types.NULL);
		}
		this.ps.setDate(4, new java.sql.Date(vo.getHiredate().getTime()));
		this.ps.setInt(5, vo.getSal());
		this.ps.setInt(6, vo.getComm());
		if (vo.getDept() != null)
		{
			this.ps.setInt(7, vo.getDept().getDeptno());
		} else
		{
			this.ps.setNull(7, Types.NULL);
		}
		
		this.ps.setString(8, vo.getPhoto());
		this.ps.setString(9, vo.getNote());

		this.ps.setInt(10, vo.getEmpno());

		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception
	{
		return super.deleteHandle(ids, "emp", "empno");
	}

	@Override
	public Emp findById(Integer id) throws Exception
	{
		Emp emp = null;
		String sql = " SELECT empno,ename,job,hiredate,sal,comm,photo,note FROM emp WHERE empno = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getInt(5));
			emp.setComm(rs.getInt(6));
			emp.setPhoto(rs.getString(7));
			emp.setNote(rs.getString(8));
			
		}
		return emp;
	}

	@Override
	public List<Emp> findAll() throws Exception
	{
		List<Emp> allEmps = new ArrayList<Emp>();
		String sql = " SELECT empno,ename,job,hiredate,sal,comm,photo,note FROM emp  ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next())
		{
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getInt(5));
			emp.setComm(rs.getInt(6));
			emp.setPhoto(rs.getString(7));
			emp.setNote(rs.getString(8));
			
			allEmps.add(emp);
		}
		return allEmps;
	}

	@Override
	public List<Emp> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception
	{
		List<Emp> allEmps = new ArrayList<Emp>();
		String sql = " SELECT empno,ename,job,hiredate,sal,comm,photo,note FROM emp WHERE " + column
				+ " LIKE ? LIMIT ?,? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, super.setKeyWord(keyWord));
		this.ps.setInt(2, (currentPage - 1) * lineSize);
		this.ps.setInt(3, lineSize);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next())
		{
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getInt(5));
			emp.setComm(rs.getInt(6));
			emp.setPhoto(rs.getString(7));
			emp.setNote(rs.getString(8));
			allEmps.add(emp);
		}
		return allEmps;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception
	{
		return super.countHandle("EMP", column, keyWord);
	}

	@Override
	public void doRemoveByDeptno(Integer deptno) throws Exception
	{
		String sql = " DELETE FROM emp WHERE deptno = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, deptno);
		this.ps.executeUpdate();
	}

	@Override
	public Emp findByIdDetails(Integer empno) throws Exception
	{
		Emp emp = null;
		String sql = " SELECT e.empno,e.ename,e.job,e.hiredate,e.sal,e.comm,m.empno,m.ename,d.deptno,d.dname,e.photo,e.note "
				+ " FROM emp e "
				+ " LEFT JOIN emp m ON e.mgr = m.empno "
				+ " LEFT JOIN dept d ON e.deptno = d.deptno "
				+ " WHERE e.empno =? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, empno);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getInt(5));
			emp.setComm(rs.getInt(6));
			
			Emp mgr = new Emp();
			mgr.setEmpno(rs.getInt(7));
			mgr.setEname(rs.getString(8));
			emp.setMgr(mgr);
			
			Dept dept = new Dept();
			dept.setDeptno(rs.getInt(9));
			dept.setDname(rs.getString(10));
			emp.setDept(dept);
			
			
			emp.setPhoto(rs.getString(11));
			emp.setNote(rs.getString(12));
		}
		return emp;
	}

	@Override
	public List<Emp> findAllSplitDetails(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception
	{
		List<Emp> allEmps = new ArrayList<Emp>();
		String  sql = "SELECT e.empno,e.ename,e.job,e.hiredate,e.sal,e.comm,m.empno,m.ename,d.deptno,d.dname,e.photo,e.note FROM emp e " 
		 + " LEFT JOIN emp m ON e.mgr = m.empno "
		 + " LEFT JOIN dept d ON e.deptno = d.deptno "
		 + " WHERE e."+column+" LIKE ? LIMIT ?,?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, super.setKeyWord(keyWord));
		this.ps.setInt(2, super.setLimit(currentPage, lineSize));
		this.ps.setInt(3, lineSize);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getInt(5));
			emp.setComm(rs.getInt(6));
			
			Emp mgr = new Emp();
			mgr.setEmpno(rs.getInt(7));
			mgr.setEname(rs.getString(8));
			emp.setMgr(mgr);
			
			Dept dept = new Dept();
			dept.setDeptno(rs.getInt(9));
			dept.setDname(rs.getString(10));
			emp.setDept(dept);
			
			emp.setPhoto(rs.getString(11));
			emp.setNote(rs.getString(12));
			
			
			allEmps.add(emp);
		}
		return allEmps;
	}

	@Override
	public List<Emp> findAllSplitDetailsByDeptno(Integer deptno, String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception
	{
		List<Emp> allEmps = new ArrayList<Emp>();
		String  sql = "SELECT e.empno,e.ename,e.job,e.hiredate,e.sal,e.comm,m.empno,m.ename,d.deptno,d.dname,e.photo,e.note FROM emp e " 
		 + " LEFT JOIN emp m ON e.mgr = m.empno "
		 + " LEFT JOIN dept d ON e.deptno = d.deptno "
		 + " WHERE e.deptno=? AND e."+column+" LIKE ? LIMIT ?,?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, deptno);
		this.ps.setString(2, super.setKeyWord(keyWord));
		this.ps.setInt(3, super.setLimit(currentPage, lineSize));
		this.ps.setInt(4, lineSize);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getInt(5));
			emp.setComm(rs.getInt(6));
			
			Emp mgr = new Emp();
			mgr.setEmpno(rs.getInt(7));
			mgr.setEname(rs.getString(8));
			emp.setMgr(mgr);
			
			Dept dept = new Dept();
			dept.setDeptno(rs.getInt(9));
			dept.setDname(rs.getString(10));
			emp.setDept(dept);
			
			emp.setPhoto(rs.getString(11));
			emp.setNote(rs.getString(12));
			
			allEmps.add(emp);
		}
		return allEmps;
	}

	@Override
	public List<String> findAllPhotosByDeptno(Set<Integer> ids) throws Exception
	{
		List<String> allPhotos = new ArrayList<String>();
		StringBuffer sql = new StringBuffer(" SELECT photo FROM emp WHERE empno IN( ");
		for(Integer id : ids)
		{
			sql.append(id).append(",");
		}
		sql.delete(sql.length()-1, sql.length());
		sql.append(")");
		this.ps = this.conn.prepareStatement(sql.toString());
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			allPhotos.add(rs.getString("photo"));
		}
		return allPhotos;
	}
}
