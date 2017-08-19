package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;







import vo.Dept;
import dao.IDeptDAO;

public class DeptDAOImpl implements IDeptDAO
{
	Connection conn = null;
	PreparedStatement ps = null;
	
	public DeptDAOImpl(Connection conn)
	{
		super();
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Dept vo) throws SQLException
	{
		String sql = " INSERT INTO DEPT(DEPTNO,DNAME,LOC) VALUES(?,?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, vo.getDeptno());
		this.ps.setString(2, vo.getDname());
		this.ps.setString(3, vo.getLoc());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(Dept vo) throws SQLException
	{
		String sql = " UPDATE DEPT SET DEPTNO=?,DNAME=?,LOC=? WHERE DEPTNO=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, vo.getDeptno());
		this.ps.setString(2, vo.getDname());
		this.ps.setString(3, vo.getLoc());
		this.ps.setInt(4, vo.getDeptno());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException
	{
		if(ids.size() <= 0)
			return false;
		
		StringBuffer sql = new StringBuffer(" DELETE FROM DEPT WHERE DEPTNO IN( ");
		for(Integer id : ids)
		{
			sql.append(id).append(",");
		}
		sql.delete(sql.length()-1, sql.length());
		sql.append(" )");
		this.conn.setAutoCommit(false);
		this.ps = this.conn.prepareStatement(sql.toString());
		if(this.ps.executeUpdate() == ids.size())
		{
			this.conn.commit();
			this.conn.setAutoCommit(true); 
			return true;
		}
		else
		{
			this.conn.rollback();
			this.conn.setAutoCommit(true); 
			return false;
		}
		
	}

	@Override
	public Dept findById(Integer id) throws SQLException
	{
		Dept vo = null;
		String sql = " SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			vo = new Dept();
			vo.setDeptno(rs.getInt("DEPTNO"));
			vo.setDname(rs.getString("DNAME"));
			vo.setLoc(rs.getString("LOC"));
		}
		return vo;
	}

	@Override
	public List<Dept> findAll() throws SQLException
	{
		List<Dept> allDepts = new ArrayList<Dept>();
		String sql = " SELECT DEPTNO,DNAME,LOC FROM DEPT ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Dept vo = new Dept();
			vo.setDeptno(rs.getInt("DEPTNO"));
			vo.setDname(rs.getString("DNAME"));
			vo.setLoc(rs.getString("LOC"));
			allDepts.add(vo);
		}
		return allDepts;
	}

	@Override
	public List<Dept> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

 

}
