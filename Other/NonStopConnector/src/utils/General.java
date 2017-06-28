package utils;

import java.io.File;
import java.text.SimpleDateFormat; 
import java.util.Date;
import java.util.List; 

public class General
{
	
	public static String getCurrentTime()
	{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()).toString();
	}
	public static java.sql.Timestamp getCurrentSqlDate()
	{
		return new java.sql.Timestamp(System.currentTimeMillis());
	}
	
	public static void removePhotos(List<String> allPhotos)
	{
		for(String photo : allPhotos)
		{
			File file = new File(photo);
			if(file.exists())
				file.delete();
		}
	}
	 
	public static String setSystemError(Exception e)
	{
		e.printStackTrace();
		return CONST.errorPage;
	} 
}
