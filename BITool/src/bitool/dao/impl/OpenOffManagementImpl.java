package bitool.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import bitool.dao.IOpenOffManagement;
import bitool.vo.OpenOffManagement;

public class OpenOffManagementImpl implements IOpenOffManagement
{
	private Connection conn = null;
	
	public OpenOffManagementImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	@Override
	public OpenOffManagement find() throws Exception
	{
		OpenOffManagement vo = null;
		String sql = " SELECT oid,mode,startTime,endTime,modtime FROM openOffManagement LIMIT 0,1 ";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			vo = new OpenOffManagement();
			vo.setOid(rs.getInt("oid")); 
			vo.setMode(rs.getString("mode"));
			vo.setStartTime(rs.getString("startTime"));
			vo.setEndTime(rs.getString("endTime"));
			vo.setModtime(rs.getTimestamp("modtime"));
		}
		return vo;
	}

	@Override
	public boolean updateTime(OpenOffManagement vo) throws Exception
	{
		
		
		String sql = " UPDATE OpenOffManagement SET startTime=?,endTime=?,modtime=? ";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, vo.getStartTime());
		ps.setString(2, vo.getEndTime());
		ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		return ps.executeUpdate() == 1;
	}

//	@Override
//	public boolean updateStatus(String status) throws Exception
//	{
//		String sql = " UPDATE OpenOffManagement SET status=? ";
//		PreparedStatement ps = this.conn.prepareStatement(sql);
//		ps.setString(1, status);
//		return ps.executeUpdate() == 1;
//	}

	@Override
	public boolean updateMode(String mode) throws Exception
	{ 
		String sql = " UPDATE OpenOffManagement SET mode=?,modtime=? ";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, mode);
		ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
		return ps.executeUpdate() == 1;
	}

}
