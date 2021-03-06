package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;





import dao.AbstractDAOImpl;
import dao.IDeptDAO;
import vo.Dept;

public class DeptDAOImpl extends AbstractDAOImpl implements IDeptDAO
{

	public DeptDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(Dept vo) throws SQLException
	{
		String sql = " INSERT INTO dept(dname,current) VALUES(?,0) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getDname());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(Dept vo) throws SQLException
	{
		//不能更新current字段 current 表示当前部门人数
		String sql = " UPDATE dept SET dname=? WHERE did=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getDname());
		this.ps.setInt(2, vo.getDid());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException
	{
		return super.removeHandler("dept", "did", ids);
	}

	@Override
	public Dept findById(Integer id) throws SQLException
	{
		Dept dept = null;
		String sql = " SELECT did,dname,current FROM dept WHERE did=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			dept = new Dept();
			 
			dept.setDid(rs.getInt("did"));
			dept.setDname(rs.getString("dname"));
			dept.setCurrent(rs.getInt("current"));
		}
		
		
		return dept;
	}

	@Override
	public List<Dept> findAll() throws SQLException
	{
		List<Dept> allDepts = new ArrayList<Dept>();
		String sql = " SELECT did,dname,current FROM dept ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Dept vo = new Dept();
			vo.setDid(rs.getInt("did"));
			vo.setDname(rs.getString("dname"));
			vo.setCurrent(rs.getInt("current"));
			allDepts.add(vo);
		} 
		return allDepts;
	}

	@Override
	public List<Dept> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findByDname(String dname) throws SQLException
	{
		String sql = " SELECT COUNT(*) FROM dept WHERE dname=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, dname);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			if(rs.getInt(1) > 0)
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public boolean findByDnameAndId(String dname, Integer did) throws SQLException
	{
		String sql = " SELECT COUNT(*) FROM dept WHERE dname=? AND did<>? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, dname);
		this.ps.setInt(2, did);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			if(rs.getInt(1) > 0)
				return true;
			else
				return false;
		}
		return false;
	}
}
