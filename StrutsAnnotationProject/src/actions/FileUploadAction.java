package actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import utils.FileUploadUtil;

import com.opensymphony.xwork2.ActionSupport;

import exceptions.NotSupportedFileTypeException;

@SuppressWarnings("serial")
@ParentPackage("root")
@Namespace("/pages/fileUploadDemo")
@Action("FileUploadAction")
@Results({@Result(name="input",location="/pages/fileUploadDemo/input.jsp")}) 
public class FileUploadAction extends ActionSupport
{ 
	private String msg ;
	
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	private File photo;
	private String photoFileName;
	private String photoContentType;
	
	public void setPhotoFileName(String photoFileName)
	{
		this.photoFileName = photoFileName;
	}
	public String getPhotoFileName()
	{
		return photoFileName;
	}
	public void setPhotoContentType(String photoContentType)
	{
		this.photoContentType = photoContentType;
	}
	public String getPhotoContentType()
	{
		return this.photoContentType;
	}
	
	public void setPhoto(File photo)
	{
		this.photo = photo;
	}
	public File getPhoto()
	{
		return photo;
	}

	public void upload()
	{
		ServletContext application = ServletActionContext.getServletContext(); 
		FileUploadUtil fileUploadUtil = new FileUploadUtil(this.photo, application.getRealPath("/upload") + File.separator + "photos" + File.separator, this.photoContentType, this.photoFileName);
		String newFileName;
		try
		{
			newFileName = fileUploadUtil.save();
			System.out.println("[debug]:" + newFileName + " has been saved ");
		} catch (NotSupportedFileTypeException | IOException e)
		{
			System.out.println("[debug]: 文件上传失败." + e.getMessage());
		}
	}
}