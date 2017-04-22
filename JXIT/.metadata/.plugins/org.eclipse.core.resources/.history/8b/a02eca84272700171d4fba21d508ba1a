package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.jspsmart.upload.SmartFiles;
import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;

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
	
	public static String getRequestStatus(HttpServletRequest request)
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
		return CONST.forwardPage;
	}
	
	public static String setSystemError(Exception e)
	{
		e.printStackTrace();
		return CONST.errorPage;
	}
	
	public static String updatePhoto(SmartUpload smart, String oldPhoto, String photo, HttpServletRequest _request, String photoPath) throws Exception
	{
		SmartFiles files= smart.getFiles();
		boolean hasNewPhoto = false;
		if(files.getCount() > 0 && files.getSize() > 0 && files.getFile(0).getContentType().contains("image"))
			hasNewPhoto = true;
		
		if(CONST.noPhoto.equals(oldPhoto))
		{ 
			if(hasNewPhoto)
			{
				//原来没有 现在有
				photo = UUID.randomUUID() + "." + files.getFile(0).getFileExt();
				//保存新照片
				files.getFile(0).saveAs(_request.getServletContext().getRealPath(photoPath + photo));
			}
			else
			{
				//原来没有 现在也没有
				photo = CONST.noPhoto;
			}
		}
		else
		{
			if(hasNewPhoto)
			{
				//原来有 现在有
				photo = UUID.randomUUID() + "." + files.getFile(0).getFileExt();
				//保存新照片
				files.getFile(0).saveAs(_request.getServletContext().getRealPath(photoPath + photo));
				
				//删掉旧照片
				List<String> photos = new ArrayList<String>();
				photos.add(_request.getServletContext().getRealPath(photoPath + oldPhoto));
				General.removePhotos(photos);
			}
			else
			{
				//原来有 现在没有
				photo = oldPhoto;
			}
		}
		
		return photo;
	}
}
