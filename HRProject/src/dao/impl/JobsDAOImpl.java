package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dao.AbstractDAOImpl;
import dao.IJobsDAO;
import vo.Jobs;

public class JobsDAOImpl extends AbstractDAOImpl implements IJobsDAO
{

	public JobsDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(Jobs vo) throws SQLException
	{
		String sql = " INSERT INTO jobs(title,note) VALUES(?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getTitle());
		this.ps.setString(2, vo.getNote());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(Jobs vo) throws SQLException
	{
		String sql = " UPDATE jobs SET title=?,note=? WHERE jid=? " ;
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getTitle());
		this.ps.setString(2, vo.getNote());
		this.ps.setInt(3, vo.getJid());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException
	{
		return super.removeHandler("jobs", "jid", ids);
	}

	@Override
	public Jobs findById(Integer id) throws SQLException
	{
		Jobs vo = null;
		String sql = " SELECT jid,title,note FROM jobs WHERE jid=? ";
		
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			vo = new Jobs();
			vo.setJid(rs.getInt("jid"));
			vo.setTitle(rs.getString("title"));
			vo.setNote(rs.getString("note"));
			
		}
		
		return vo;
	}

	@Override
	public List<Jobs> findAll() throws SQLException
	{
		List<Jobs> allJobs = new ArrayList<Jobs>();
		String sql = " SELECT jid,title,note FROM jobs ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
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

	@Override
	public boolean findByTitle(String title) throws SQLException
	{
		String sql = " SELECT COUNT(*) FROM jobs WHERE title =? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, title);
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
	public boolean findByTitleAndId(String title, Integer jid) throws SQLException
	{
		String sql = " SELECT COUNT(*) FROM jobs WHERE title=? AND jid<>? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, title);
		this.ps.setInt(2, jid);
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
