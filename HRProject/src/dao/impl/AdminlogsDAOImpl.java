package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import dao.AbstractDAOImpl;
import dao.IAdminlogsDAO;
import utils.General;
import vo.Adminlogs;

public class AdminlogsDAOImpl extends AbstractDAOImpl implements IAdminlogsDAO
{

	public AdminlogsDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(Adminlogs vo) throws SQLException
	{
		String sql = " INSERT INTO adminlogs(aid,logindate) VALUES(?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getAdmin().getAid());
		this.ps.setTimestamp(2, General.getCurrentSqlDate());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(Adminlogs vo) throws SQLException
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
	public Adminlogs findById(Integer id) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adminlogs> findAll() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adminlogs> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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
