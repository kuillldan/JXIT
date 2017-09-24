package org.lyk.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FileAction extends ActionSupport
{
	private File photo1;
	private String photo1FileName;
	private String photo1ContentType;
	
	public void setPhoto1FileName(String photo1FileName)
	{
		this.photo1FileName = photo1FileName;
	}
	public void setPhoto1ContentType(String photo1ContentType)
	{
		this.photo1ContentType = photo1ContentType;
	}
	
	public File getPhoto1()
	{
		return photo1;
	}
	public void setPhoto1(File photo1)
	{
		this.photo1 = photo1;
	}
	
	public String insert()
	{
		System.out.println(this.photo1FileName);
		System.out.println(this.photo1ContentType);
		String filePath = ServletActionContext.getServletContext().getRealPath("/upload/");
		filePath += UUID.randomUUID() + this.photo1FileName;
		File target = new File(filePath);
		if(!target.getParentFile().exists())
		{
			target.getParentFile().mkdirs();
		}
		try
		{
			InputStream is = new FileInputStream(this.photo1);
			OutputStream os = new FileOutputStream(target);
			
			byte[] buffer = new byte[1024*1024];
			int bytesReaded = -1;
			while((bytesReaded = is.read(buffer, 0, 1024*1024)) != -1)
			{
				os.write(buffer, 0, bytesReaded);
			}
			
			is.close();
			os.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
			
		return "success";
	}
}
