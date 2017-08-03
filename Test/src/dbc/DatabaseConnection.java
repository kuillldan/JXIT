package dbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection
{
//	public static final String CLASSNAME = "com.mysql.jdbc.Driver";
//	public static final String USER = "root";
//	public static final String PASSWORD = "admin";
//	public static final String URL = "jdbc:mysql://localhost:3306/hrdb";
	
	public static final String CLASSNAME = "oracle.jdbc.driver.OracleDriver";
	public static final String USER = "freeway";
	public static final String PASSWORD = "ts-tc-218";
	public static final String URL = "jdbc:oracle:thin:@172.21.3.3:1521:aeg2lg1";
	
	private Connection conn;
	
	public DatabaseConnection() 
	{
		try
		{
			Class.forName(CLASSNAME);
			this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
		} catch (Exception e)
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
		if(this.conn != null)
		{
			try
			{
				this.conn.close();
				System.out.println("[debug]: connection closed" );
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
