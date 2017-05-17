package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import exceptions.NotSupportedFileException;

public class UploadFileHelper
{
	private File file ;
	private String contentType;
	private String fileName;
	public UploadFileHelper(File file, String contentType)
	{
		this.file = file;
		this.contentType = contentType;
	}
	
	public String save(String path)throws IOException 
	{
		generateFileName();
		
		File targetFile = new File(path+this.fileName);
		if(!targetFile.getParentFile().exists())
		{
			targetFile.getParentFile().mkdirs();
		}
		
		InputStream is = new FileInputStream(this.file);
		OutputStream os = new FileOutputStream(targetFile);
		byte[] buffer = new byte[1024];
		int len = -1;
		while((len = is.read(buffer)) > -1)
		{
			os.write(buffer, 0, len);
		}
		is.close();
		os.close();
		return fileName;
	}
	
	private void generateFileName()  
	{
		String fileName = UUID.randomUUID().toString();
		if("image/bmp".equalsIgnoreCase(this.contentType))
		{
			this.fileName = fileName + ".bmp";
		}
		else if("image/gif".equalsIgnoreCase(this.contentType))
		{
			this.fileName = fileName + ".gif";
		}else if("image/jpeg".equalsIgnoreCase(this.contentType))
		{
			this.fileName = fileName + ".jpg";
		}else if("image/png".equalsIgnoreCase(this.contentType))
		{
			this.fileName = fileName + ".png";
		}
	}
}
