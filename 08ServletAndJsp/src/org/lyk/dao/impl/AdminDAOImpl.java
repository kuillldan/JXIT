package org.lyk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

import org.lyk.dao.IAdminDAO;
import org.lyk.vo.Admin;

public class AdminDAOImpl implements IAdminDAO
{
	
	private Connection conn;
	private PreparedStatement ps ;
	
	public AdminDAOImpl(Connection conn)
	{
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Admin vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Admin vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Admin findById(String id) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findLogin(Admin admin) throws Exception
	{
		String sql = " SELECT COUNT(*) FROM admin WHERE aid = ? AND password=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, admin.getAid());
		this.ps.setString(2, admin.getPassword());
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			return rs.getInt(1) == 1 ;
		}
		return false;
	}

}
