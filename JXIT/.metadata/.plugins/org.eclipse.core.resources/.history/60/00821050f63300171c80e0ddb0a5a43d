package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.PreparedHandler;
import dao.ResultSetter;
import dao.SqlCallback;

public class AbstractDAOImpl
{
	protected Connection conn;
	protected PreparedStatement ps ;
	
	public AbstractDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public Object query(SqlCallback sqlCallback, PreparedHandler preparedHandler, ResultSetter resultSetter)throws SQLException
	{
		Object vo = null;
		String sql = sqlCallback.getSqlString();
		this.ps = this.conn.prepareStatement(sql);
		preparedHandler.setParameter(this.ps);
		ResultSet rs = this.ps.executeQuery();
		vo = resultSetter.getResult(rs);
		return vo;
	}
}
