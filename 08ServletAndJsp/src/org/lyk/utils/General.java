package org.lyk.utils;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class General
{
	public static void removePhotos(List<String> allPhotos)
	{
		for(String photo : allPhotos)
		{
			File file = new File(photo);
			if(file.exists())
				file.delete();
		}
	}
	
	public static String getStatus(HttpServletRequest request)
	{
		return request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
	}
	
}
