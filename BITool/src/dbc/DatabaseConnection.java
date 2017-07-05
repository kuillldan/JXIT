package dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
	private static final String CLASSNAME = "org.gjt.mm.mysql.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/bitool";
	private static final String USER = "root";
	private static final String PASSWORD = "admin";
	
	private Connection conn;
	
	public DatabaseConnection()
	{
		try
		{
			Class.forName(CLASSNAME);
			this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
		}
		catch(Exception e)
		{
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
				e.printStackTrace();
			}
		}
	}
	
}
