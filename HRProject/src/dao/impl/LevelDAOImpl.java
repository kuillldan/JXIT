package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set; 

import vo.Level;
import dao.AbstractDAOImpl;
import dao.ILevelDAO;

public class LevelDAOImpl extends AbstractDAOImpl implements ILevelDAO
{
	public LevelDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

 
	@Override
	public List<vo.Level> findAll() throws SQLException
	{
		List<vo.Level> allLevels = new ArrayList<vo.Level>();
		String sql = " SELECT levid,title,losal,hisal FROM level ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			vo.Level vo = new vo.Level();
			vo.setLevid(rs.getInt("levid"));
			vo.setTitle(rs.getString("title"));
			vo.setLosal(rs.getDouble("losal"));
			vo.setHisal(rs.getDouble("hisal"));
			allLevels.add(vo);
		}
		return allLevels;
	}

	@Override
	public List<Level> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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
		String sql = " SELECT COUNT(*) FROM level WHERE title = ? ";
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
	public boolean findByTitleAndId(String title, Integer levid) throws SQLException
	{
		String sql = " SELECT COUNT(*) FROM level WHERE title=? AND levid<>? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, title);
		this.ps.setInt(2, levid);
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
	public boolean doCreate(vo.Level vo) throws SQLException
	{
		String sql = " INSERT INTO level(title,losal,hisal) VALUES(?,?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getTitle());
		this.ps.setDouble(2, vo.getLosal());
		this.ps.setDouble(3, vo.getHisal());
		return this.ps.executeUpdate() == 1;
	}


	@Override
	public boolean doUpdate(vo.Level vo) throws SQLException
	{
		String sql = " UPDATE level SET title=?,losal=?,hisal=? WHERE levid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getTitle());
		this.ps.setDouble(2, vo.getLosal());
		this.ps.setDouble(3, vo.getHisal());
		this.ps.setInt(4, vo.getLevid());
		return this.ps.executeUpdate() == 1;
	}


	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException
	{
		return super.removeHandler("level", "levid", ids);
	}


	@Override
	public vo.Level findById(Integer id) throws SQLException
	{
		Level vo = null;
		String sql = " SELECT levid,title,losal,hisal FROM level WHERE levid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			vo = new Level();
			vo.setLevid(rs.getInt("levid"));
			vo.setTitle(rs.getString("title"));
			vo.setLosal(rs.getDouble("losal"));
			vo.setHisal(rs.getDouble("hisal")); 
		}
		return vo;
	}

}
