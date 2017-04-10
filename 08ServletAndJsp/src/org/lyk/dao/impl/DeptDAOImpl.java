package org.lyk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.lyk.dao.AbstractDAO;
import org.lyk.dao.IDeptDAO;
import org.lyk.vo.Dept;

public class DeptDAOImpl extends AbstractDAO implements IDeptDAO
{
	private PreparedStatement ps;

	public DeptDAOImpl(Connection conn)
	{
		super(conn);
	}

	@Override
	public boolean doCreate(Dept vo) throws Exception
	{
		String sql = " INSERT INTO dept(deptno,dname,loc) VALUES(?,?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, vo.getDeptno());
		this.ps.setString(2, vo.getDname());
		this.ps.setString(3, vo.getLoc());
		return ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(Dept vo) throws Exception
	{
		String sql = " UPDATE dept SET dname=?, loc =? WHERE deptno=?";
		this.ps = this.conn.prepareStatement(sql);

		this.ps.setString(1, vo.getDname());
		this.ps.setString(2, vo.getLoc());
		this.ps.setInt(3, vo.getDeptno());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception
	{
		return super.deleteHandle(ids, "dept", "deptno");
	}

	@Override
	public Dept findById(Integer id) throws Exception
	{
		Dept vo = null;
		String sql = " SELECT deptno,dname,loc FROM DEPT WHERE deptno =? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			vo = new Dept();
			vo.setDeptno(rs.getInt("deptno"));
			vo.setDname(rs.getString("dname"));
			vo.setLoc(rs.getString("loc"));
		}

		return vo;
	}

	@Override
	public List<Dept> findAll() throws Exception
	{
		List<Dept> allDepts = new ArrayList<Dept>();

		String sql = " SELECT deptno,dname,loc FROM dept ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next())
		{
			Dept vo = new Dept();
			vo.setDeptno(rs.getInt("deptno"));
			vo.setDname(rs.getString("dname"));
			vo.setLoc(rs.getString("loc"));
			allDepts.add(vo);
		}
		return allDepts;
	}

	@Override
	public List<Dept> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception
	{
		return super.countHandle("dept", column, keyWord);
	}

	@Override
	public List<Dept> findAllWithStat() throws Exception
	{
		List<Dept> allDepts = new ArrayList<Dept>();
		String sql = " SELECT d.deptno,d.dname,d.loc,e.sum,e.avg,e.max,e.min FROM dept d LEFT JOIN ( SELECT SUM(SAL) sum,AVG(SAL) avg,MAX(SAL) max,MIN(SAL) min,deptno FROM emp GROUP BY deptno) e  ON d.deptno = e.deptno ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next())
		{
			Dept dept = new Dept();
			dept.setDeptno(rs.getInt("deptno"));
			dept.setDname(rs.getString("dname"));
			dept.setLoc(rs.getString("dname"));
			Map<String, Object> stat = new HashMap<String, Object>();
			stat.put("sum", rs.getDouble("sum"));
			stat.put("avg", rs.getDouble("avg"));
			stat.put("max", rs.getDouble("max"));
			stat.put("min", rs.getDouble("min"));
			dept.setStat(stat);
			allDepts.add(dept);
		}
		return allDepts;
	}

	@Override
	public Dept findByIdWithStat(Integer deptno) throws Exception
	{
		Dept dept = null;
		String sql = " SELECT d.deptno,d.dname,d.loc,e.sum,e.avg,e.max,e.min,e.count FROM dept d LEFT JOIN ( SELECT COUNT(*) count, SUM(SAL) sum,AVG(SAL) avg,MAX(SAL) max,MIN(SAL) min,deptno FROM emp GROUP BY deptno) e  ON d.deptno = e.deptno WHERE d.deptno=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, deptno);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			dept = new Dept();
			dept.setDeptno(rs.getInt("deptno"));
			dept.setDname(rs.getString("dname"));
			dept.setLoc(rs.getString("dname"));
			Map<String, Object> stat = new HashMap<String, Object>();
			stat.put("sum", rs.getDouble("sum"));
			stat.put("avg", rs.getDouble("avg"));
			stat.put("max", rs.getDouble("max"));
			stat.put("min", rs.getDouble("min"));
			stat.put("count", rs.getInt("count"));
			dept.setStat(stat);
		}
		return dept;
	}

}
