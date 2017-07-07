package bitool.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


public class General
{ 
	public static String getStatus(HttpServletRequest request)
	{
		return request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
	}
	
	public static String getBasePath(HttpServletRequest request)
	{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		return basePath;
	}
	
	public static String setMsgAndUrlInRequest(HttpServletRequest request, String msg , String url)
	{
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return CONST.forwardPageJSP;
	}
	
	public static String setSystemError(Exception e)
	{
		e.printStackTrace();
		return CONST.errorPageJSP;
	} 
}
