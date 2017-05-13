package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mysql.jdbc.ResultSet;

import vo.Jobs;
import dao.IJobs;

public class JobsDAOImpl extends AbstractDAOImpl implements IJobs
{
	public JobsDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(Jobs vo) throws SQLException
	{
		String sql = " INSERT INTO jobs (title,note) VALUES(?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getTitle());
		this.ps.setString(2, vo.getNote());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(Jobs vo) throws SQLException
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
	public Jobs findById(Integer id) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jobs> findAll() throws SQLException
	{
		List<Jobs> allJobs = new ArrayList<Jobs>();
		String sql = " SELECT jid,title,note FROM jobs ";
		this.ps = this.conn.prepareStatement(sql);
		java.sql.ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Jobs vo = new Jobs();
			vo.setJid(rs.getInt("jid"));
			vo.setTitle(rs.getString("title"));
			vo.setNote(rs.getString("note"));
			allJobs.add(vo);
		}
		return allJobs;
	}

	@Override
	public List<Jobs> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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
