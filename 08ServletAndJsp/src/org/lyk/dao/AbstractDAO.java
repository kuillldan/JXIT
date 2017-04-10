package org.lyk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
 

public abstract class AbstractDAO
{
	protected Connection conn;
	public AbstractDAO(Connection conn)
	{
		this.conn = conn;
	}
	
	
	public boolean deleteHandle(Set<?> ids,String tableName, String primaryColumn) throws Exception
	{
		StringBuffer sql = new StringBuffer(" DELETE FROM "+tableName+" WHERE "+primaryColumn+" IN( ");
		for(Object id : ids)
		{
			sql.append(id).append(" ,");
		}
		sql.delete(sql.length()-1, sql.length()).append(") "); 
		PreparedStatement ps = this.conn.prepareStatement(sql.toString());
		return ps.executeUpdate() == ids.size();
	}
	
	
	public Integer countHandle(String tableName, String column, String keyWord) throws Exception
	{
		String sql = " SELECT count(*) FROM " + tableName + " WHERE " + column + " LIKE ? ";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, "%"+keyWord+"%");
		ResultSet rs = ps.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		else
			return 0;
	}
	
	protected String setKeyWord(String keyWord)
	{
		return "%"+keyWord+"%";
	}
	
	protected Integer setLimit(Integer currentPage, Integer lineSize)
	{
		return (currentPage - 1) * lineSize;
	}
}
