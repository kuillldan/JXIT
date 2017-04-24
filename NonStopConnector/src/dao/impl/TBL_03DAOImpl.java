package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import vo.TBL_03;
import dao.AbstractDAOImpl;
import dao.ITBL_03DAO;

public class TBL_03DAOImpl extends AbstractDAOImpl implements ITBL_03DAO
{

	public TBL_03DAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(TBL_03 vo) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(TBL_03 vo) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TBL_03 findById(String id) throws SQLException
	{
		TBL_03 vo = null;
		String sql = " select COL_03_001,COL_03_002,COL_03_003,COL_03_004,COL_03_005,COL_03_006,COL_03_007,"
				+ "COL_03_008,COL_03_009,COL_03_010,COL_03_011,COL_03_012,COL_03_013,COL_03_014,COL_03_015,"
				+ "COL_03_016 from CTFAKSPRD.SCFAKPRD.TBL_03 where COL_03_001 = ? order by COL_03_002 ASC ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, id);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			vo = new TBL_03();
			vo.setCOL_03_001(rs.getString("COL_03_001"));
			vo.setCOL_03_002(rs.getInt("COL_03_002"));
			vo.setCOL_03_003(rs.getString("COL_03_003"));
			vo.setCOL_03_004(rs.getString("COL_03_004"));
			vo.setCOL_03_005(rs.getString("COL_03_005"));
			vo.setCOL_03_006(rs.getString("COL_03_006"));
			vo.setCOL_03_007(rs.getString("COL_03_007"));
			vo.setCOL_03_008(rs.getString("COL_03_008"));
			vo.setCOL_03_009(rs.getString("COL_03_009"));
			vo.setCOL_03_010(rs.getString("COL_03_010"));
			vo.setCOL_03_011(rs.getString("COL_03_011"));
			vo.setCOL_03_012(rs.getString("COL_03_012"));
			vo.setCOL_03_013(rs.getString("COL_03_013"));
			vo.setCOL_03_014(rs.getString("COL_03_014"));
			vo.setCOL_03_015(rs.getString("COL_03_015"));
			vo.setCOL_03_016(rs.getString("COL_03_016"));
		}
		return vo;
	}

	@Override
	public List<TBL_03> findAll() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TBL_03> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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
