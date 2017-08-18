package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;


public abstract class AbstractDAOImpl<K,V>
{
	protected Connection conn;
	protected PreparedStatement ps;
	public AbstractDAOImpl(Connection conn)
	{
		super();
		this.conn = conn;
	}

	public boolean doRemoveHelper(Set<Integer> ids,String tableName,String column) throws SQLException
	{
		if(ids.size() <= 0)
			return false;
		
		StringBuffer sql = new StringBuffer(" DELETE FROM "+tableName+" WHERE "+column+" IN( ");
		for(Integer id : ids)
		{
			sql.append(id).append(",");
		}
		sql.delete(sql.length()-1, sql.length());
		sql.append(" )");
		 
		this.ps = this.conn.prepareStatement(sql.toString());
		if(this.ps.executeUpdate() == ids.size())
		{ 
			return true;
		}
		else
		{ 
			return false;
		}
	}
	
	public Integer getAllCountHelper(String tableName) throws SQLException
	{
		String sql = " SELECT COUNT(*) FROM " + tableName;
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			return rs.getInt(1);
		}
		else
		{
			return 0;
		}
	}
	
	protected abstract void setVo(ResultSet rs, V vo) throws SQLException;
}
