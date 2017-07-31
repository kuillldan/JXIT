package bitool.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class BI_DatabaseConnection
{
	private static final ResourceBundle bi_db_rb = ResourceBundle.getBundle("BI_DB");
	
	private static final String CLASSNAME = bi_db_rb.getString("CLASSNAME");
	private static final String URL = bi_db_rb.getString("URL");
	private static final String USER = bi_db_rb.getString("USER");
	private static final String PASSWORD = bi_db_rb.getString("PASSWORD");
	
	private static final Logger logger = Logger.getLogger(BI_DatabaseConnection.class);
	
	private Connection conn;
	
	public BI_DatabaseConnection()
	{
		
		try
		{
			Class.forName(CLASSNAME);
			this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			//e.printStackTrace();
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
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			}
		}
	}
}
