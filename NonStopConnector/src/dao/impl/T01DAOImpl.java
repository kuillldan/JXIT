package dao.impl;

import  dao.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import vo.T01;
 
public class T01DAOImpl extends AbstractDAOImpl implements IT01DAO
{

	public T01DAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(T01 vo) throws SQLException
	{
		String sql = " INSERT INTO SHELDONCAT.SHELDONSCH.T01 (EMPNUM,FIRST_NAME,LAST_NAME,TEAMNUM) VALUES(1003,'YUCHUAN','ZENG',10); ";
		this.ps = this.conn.prepareStatement(sql);
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(T01 vo) throws SQLException
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
	public T01 findById(Integer id) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T01> findAll() throws SQLException
	{
		List<T01> allT01s = new ArrayList<T01>();
		String sql = " SELECT * FROM SHELDONCAT.SHELDONSCH.T01 ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			T01 vo = new T01();
			vo.setEmpnum(rs.getInt(1));
			vo.setFirst_name(rs.getString(2));
			vo.setLast_name(rs.getString(3));
			vo.setTeamnum(rs.getInt(4));
			allT01s.add(vo);
		}
		return allT01s;
	}

	@Override
	public List<T01> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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
