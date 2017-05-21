package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import exceptions.NotSupportedFileTypeException;

public class FileUploadUtil
{
	private File file;
	private String folder;
	private String contentType;
	private String fileName;

	public FileUploadUtil(File file, String folder, String contentType, String fileName)
	{
		this.file = file;
		this.folder = folder;
		this.contentType = contentType;
		this.fileName = fileName;
	}

	public String save() throws NotSupportedFileTypeException, IOException
	{
		if (!StringUtils.isTheSame("image/bmp", this.contentType)
				&& !StringUtils.isTheSame("image/gif", this.contentType)
				&& !StringUtils.isTheSame("image/jpg", this.contentType)
				&& !StringUtils.isTheSame("image/jpeg", this.contentType)
				&& !StringUtils.isTheSame("image/png", this.contentType))
		{
			throw new NotSupportedFileTypeException("本框架不支持(" + this.contentType + ")类型的文件上传");
		}

		String newFileName = this.generateName();
		File target = new File(this.folder + newFileName);
		if(!target.getParentFile().exists())
		{
			target.getParentFile().mkdirs();
		}
		
		InputStream is = new FileInputStream(this.file);
		OutputStream os = new FileOutputStream(target);
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = is.read(buffer)) > -1)
		{
			os.write(buffer, 0, len);
		}
		is.close();
		os.close();
		return newFileName; 
	}

	private String generateName()
	{
		return UUID.randomUUID() + this.fileName.substring(this.fileName.lastIndexOf("."));
	}
}