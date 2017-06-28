package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import vo.TBL_04;
import dao.AbstractDAOImpl;
import dao.ITBL_04DAO;

public class TBL_04DAOImpl extends AbstractDAOImpl implements ITBL_04DAO
{

	public TBL_04DAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(TBL_04 vo) throws SQLException
	{
		String sql = " INSERT INTO CTFAKSPRD.SCFAKPRD.TBL_04 (COL_04_001,COL_04_002,COL_04_003,COL_04_004,COL_04_005,COL_04_006,COL_04_007,COL_04_008,COL_04_009,COL_04_010,COL_04_011,COL_04_012,COL_04_013,COL_04_014,COL_04_015,COL_04_016,COL_04_017,COL_04_018,COL_04_019,COL_04_020,COL_04_021,COL_04_022,COL_04_023,COL_04_024,COL_04_025,COL_04_026,COL_04_027,COL_04_028,COL_04_029,COL_04_030,COL_04_031,COL_04_032,COL_04_033,COL_04_034,COL_04_035,COL_04_036,COL_04_037)VALUES('C1','C2','C','C4','C5','C6','C7','C8','C9','C10','C11','C12','C13','C14','C15','C16','C','C18','C','C','C21','C22','C','C24','C','C26','C27','C8','C29','C30','C31','C32','C','C34','C35','C36','C37'); ";
		this.ps = this.conn.prepareStatement(sql);
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(TBL_04 vo) throws SQLException
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
	public TBL_04 findById(String id) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TBL_04> findAll() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TBL_04> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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
