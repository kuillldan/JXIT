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

public class DeptDAOImpl extends AbstractDAOImpl implements IDeptDAO
{
	public DeptDAOImpl(Connection conn)
	{
		super(conn);
	}

	@Override
	public boolean doCreate(Dept vo) throws SQLException
	{
		System.out.println("【数据层】向数据库中插入数据:" + vo);
		return false;
	}

	@Override
	public boolean doUpdate(Dept vo) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dept findById(Integer id) throws SQLException
	{
		super.query(() -> " SELECT did,dname,current FROM dept WHERE did=? ", (ps) ->
		{
			ps.setInt(1, id);
		}, (rs) ->
		{
			Dept dept = null;
			if (rs.next())
			{
				dept = new Dept();
				dept.setDid(rs.getInt("did"));
				dept.setDname(rs.getString("dname"));
				dept.setCurrent(rs.getInt("current"));
			}

			return dept;
		});
		Dept dept = null;
		String sql = " SELECT did,dname,current FROM dept WHERE did=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			dept = new Dept();
			dept.setDid(rs.getInt("did"));
			dept.setDname(rs.getString("dname"));
			dept.setCurrent(rs.getInt("current"));
		}

		return dept;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dept> findAll() throws SQLException
	{ 
		List<Dept> allDepts = new ArrayList<Dept>();
		Dept d1 = new Dept();
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

}
