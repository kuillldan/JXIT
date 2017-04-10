package org.lyk.dbc;


import java.sql.*;

public class DatabaseConnection
{
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASSWD = "admin";
	private static final String DBURL = "jdbc:mysql://localhost:3306/mldn";
	
	private Connection conn;
	
	public DatabaseConnection()
	{
		try
		{
			Class.forName(DBDRIVER);
			this.conn = DriverManager.getConnection(DBURL, USER, PASSWD);
			
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return this.conn;
	}
	
	public void close()
	{
		if(null != this.conn)
		{
			try
			{
				this.conn.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}