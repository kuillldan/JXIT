package dao.impl;

import  dao.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import vo.TBL_01;
 
public class TBL_01DAOImpl extends AbstractDAOImpl implements ITBL_01DAO
{

	public TBL_01DAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(TBL_01 vo) throws SQLException
	{
		String sql = " INSERT INTO SHELDONCAT.SHELDONSCH.T01 (EMPNUM,FIRST_NAME,LAST_NAME,TEAMNUM) VALUES(1003,'YUCHUAN','ZENG',10); ";
		this.ps = this.conn.prepareStatement(sql);
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(TBL_01 vo) throws SQLException
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
	public TBL_01 findById(Integer id) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TBL_01> findAll() throws SQLException
	{
		String sql = "SELECT * FROM CTFAKSPRD.SCFAKPRD.TBL_01 "
				+ " where COL_01_002 = '999' "
				+ " order by COL_01_171 DESC, "
				+ " COL_01_172 DESC, "
				+ " COL_01_001 DESC ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		
		return null;
	}

	@Override
	public List<TBL_01> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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
