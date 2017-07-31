package bitool.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.params.ClientPNames;
import org.apache.log4j.Logger;




import bitool.enums.UserType;
import bitool.utils.CONST;
import bitool.dbc.BI_DatabaseConnection;

 
public class Hello
{
	public static void main(String[] args) throws Exception
	{
		//139.199.220.102
		Class.forName("org.gjt.mm.mysql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitool", "root", "admin");
		
		conn.setAutoCommit(false);
		
		PreparedStatement ps = conn.prepareStatement("select * from openOffManagement WHERE oid = 1 FOR UPDATE ");
		ResultSet rs = ps.executeQuery();
		System.out.println("===准备休眠");
		Thread.sleep(10000);
		System.out.println("====休眠结束");
		conn.commit();
		System.out.println(conn);
		conn.close();
	}
	
	public static void showString(String msg)
	{
		System.out.println(msg);
	}
	
	public static void showString(String msg,Object ... params)
	{
		System.out.println(msg);
		System.out.println(params.length + "," + params.getClass().getSimpleName());
	}
}