package dbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection
{
	private static DataSource dataSource;
	private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);

	public static Connection getConnection()
	{
		Connection conn = null;
		
		if(dataSource == null)
		{ 
			try
			{
				Context ctx = new InitialContext();
				dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/jspDemo_dbcp");
			} catch (NamingException e)
			{
				logger.error(e.getMessage(),e);
				return null;
			}
		}
		
		try
		{
			conn = dataSource.getConnection();
		} catch (SQLException e)
		{
			logger.error(e.getMessage(), e);
			return null;
		}
		return conn;
	}

	public static void close(Connection conn)
	{
		try
		{
			if (conn != null && !conn.isClosed())
			{
				conn.close();
			}
		} catch (SQLException e)
		{
			logger.error(e.getMessage(), e);
		}
	}
}
