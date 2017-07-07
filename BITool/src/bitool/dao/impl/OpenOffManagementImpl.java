package bitool.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		String sql = " SELECT oid,status,mode,startTime,endTime FROM openOffManagement LIMIT 0,1 ";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			vo = new OpenOffManagement();
			vo.setOid(rs.getInt("oid"));
			vo.setStatus(rs.getString("status"));
			vo.setMode(rs.getString("mode"));
			vo.setStartTime(rs.getString("startTime"));
			vo.setEndTime(rs.getString("endTime"));
		}
		return vo;
	}

	@Override
	public boolean updateTime(OpenOffManagement vo) throws Exception
	{
		String sql = " UPDATE OpenOffManagement SET startTime=?,endTime=? ";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, vo.getStartTime());
		ps.setString(2, vo.getEndTime());
		return ps.executeUpdate() == 1;
	}

	@Override
	public boolean updateStatus(String status) throws Exception
	{
		String sql = " UPDATE OpenOffManagement SET status=? ";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, status);
		return ps.executeUpdate() == 1;
	}

}
