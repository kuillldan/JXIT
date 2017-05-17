package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Dept;

public abstract class AbstractDAOImpl
{
	protected Connection conn;
	protected PreparedStatement ps;
	
	public AbstractDAOImpl(Connection conn)
	{
		super();
		this.conn = conn;
	}
	
	public Object query(SQLCallBack salCallBack, ParameterSetter parameterSetter, ResultHandler resultHandler) throws SQLException
	{
		String sql = salCallBack.getCommandText();
		this.ps = this.conn.prepareStatement(sql);
		parameterSetter.setParameter(this.ps);
		ResultSet rs = this.ps.executeQuery();
		return resultHandler.getResult(rs);
	}
}