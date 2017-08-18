package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import vo.Emp;
import dao.AbstractDAOImpl;
import dao.IEmpDAO;

public class EmpDAOImpl extends AbstractDAOImpl<Integer,Emp> implements IEmpDAO
{

	public EmpDAOImpl(Connection conn)
	{
		super(conn);
	}

	@Override
	public boolean doCreate(Emp vo) throws SQLException
	{
		String sql = " INSERT INTO emp(ename,job,hiredate,sal,comm) VALUES(?,?,?,?,?) ";
		super.ps = super.conn.prepareStatement(sql);
		//super.ps.setInt(1,vo.getEmpno());
		super.ps.setString(1, vo.getEname());
		super.ps.setString(2, vo.getJob());
		super.ps.setTimestamp(3, new Timestamp(vo.getHiredate().getTime()));
		super.ps.setDouble(4, vo.getSal());
		super.ps.setDouble(5, vo.getComm());
		return super.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(Emp vo) throws SQLException
	{
		String sql = " UPDATE emp SET ename=?,job=?,hiredate=?,sal=?,comm=? WHERE empno = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getEname());
		this.ps.setString(2, vo.getJob());
		this.ps.setTimestamp(3, new Timestamp(vo.getHiredate().getTime()));
		this.ps.setDouble(4, vo.getSal());
		this.ps.setDouble(5, vo.getComm());
		this.ps.setInt(6, vo.getEmpno());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException
	{
		return super.doRemoveHelper(ids, "emp", "empno");
	}

	@Override
	public Emp findById(Integer id) throws SQLException
	{
		Emp emp = null;
		String sql = " SELECT empno,ename,job,hiredate,sal,comm FROM emp WHERE empno=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			emp = new Emp();
			this.setVo(rs, emp);
		}
		return emp;
	}

	@Override
	public List<Emp> findAll() throws SQLException
	{
		List<Emp> allEmps = new ArrayList<Emp>();
		String sql = " SELECT empno,ename,job,hiredate,sal,comm FROM emp ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Emp vo = new Emp();
			this.setVo(rs, vo);
			allEmps.add(vo);
		}
		return allEmps;
	}

	@Override
	public List<Emp> findAllSplit(Integer currentPage, Integer lineSize ) throws SQLException
	{
		List<Emp> allEmps = new ArrayList<Emp>();
		String sql = " SELECT empno,ename,job,hiredate,sal,comm FROM emp LIMIT ?,? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, (currentPage - 1) * lineSize);
		this.ps.setInt(2, lineSize);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Emp vo = new Emp();
			this.setVo(rs, vo);
			allEmps.add(vo);
		}
		return allEmps;
	}

	@Override
	public Integer getAllCount() throws SQLException
	{
		return super.getAllCountHelper("emp");
	}

	@Override
	protected void setVo(ResultSet rs, Emp emp) throws SQLException
	{
		emp.setEmpno(rs.getInt("empno"));
		emp.setEname(rs.getString("ename"));
		emp.setJob(rs.getString("job"));
		emp.setHiredate(rs.getTimestamp("hiredate"));
		emp.setSal(rs.getDouble("sal"));
		emp.setComm(rs.getDouble("comm"));
	}

}
