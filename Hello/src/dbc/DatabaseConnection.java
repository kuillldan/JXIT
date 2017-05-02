package dbc;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DatabaseConnection
{
	private static final String DBDRIVER = "com.tandem.t4jdbc.SQLMXDriver";
	private static final String USER = "super.super"; //"FAK.DBMNG";// 
	private static final String PASSWD = "Kddi4BmC"; //"FACEkddi";//
//	private static final String DBURL = "jdbc:t4sqlmx://172.17.197.54:18650/";
	private static final String DBURL = "jdbc:t4sqlmx://172.17.197.54:20000/";
//	private static final String DBURL = "jdbc:t4sqlmx://172.17.10.20:20000";
	
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
