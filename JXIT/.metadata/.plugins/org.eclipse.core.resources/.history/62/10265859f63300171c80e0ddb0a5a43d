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
import dao.PreparedHandler;
import dao.ResultSetter;
import dao.SqlCallback;

public class DeptDAOImpl extends AbstractDAOImpl implements IDeptDAO
{
	public DeptDAOImpl(Connection conn)
	{
		super(conn);
	}

	@Override
	public boolean doCreate(Dept vo) throws SQLException
	{
		// TODO Auto-generated method stub
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
		return (Dept) super.query(() -> " SELECT did,dname,current FROM dept WHERE did=? ", (ps) ->
		{
			DeptDAOImpl.this.ps.setInt(1, id);
		}, (rs) ->
		{

			if (rs.next())
			{
				Dept vo = new Dept();
				vo.setDid(rs.getInt("did"));
				vo.setDname(rs.getString("dname"));
				vo.setCurrent(rs.getInt("current"));
				return vo;
			} else
			{
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dept> findAll() throws SQLException
	{
		return (List<Dept>)super.query(()->
		{
			return " SELECT did,dname,current FROM dept ";
		}, (ps)->
		{
			
		}, (rs)->
		{
			List<Dept> allDepts = new ArrayList<Dept>();
			while(rs.next())
			{
				Dept vo = new Dept();
				vo.setDid(rs.getInt("did"));
				vo.setDname(rs.getString("dname"));
				vo.setCurrent(rs.getInt("current"));
				allDepts.add(vo);
			}
			return allDepts;
		});
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
