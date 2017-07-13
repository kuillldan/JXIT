package org.lyk.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

public abstract class AbstractServlet
{
	@Resource
	private MessageSource messageSource;

	@InitBinder
	public void dateTranslator(WebDataBinder webDataBinder)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	public String getValue(String key)
	{
		return this.messageSource.getMessage(key, null, Locale.getDefault());
	}

	protected boolean saveFile(MultipartFile multipartFile, HttpServletRequest request) throws Exception
	{
		if (multipartFile.isEmpty())
			return false;
		String fileName = this.generateFileName(multipartFile);
		if (StringUtils.isEmpty(fileName))
			return false;

		String fileUploadFolder = request.getServletContext().getRealPath(
				this.formatPath(this.getUploadFolder()));
		String fileFullPath = fileUploadFolder + "/" + fileName;
		File fileToBeSaved = new File(fileFullPath);
		if (fileToBeSaved.getParentFile().exists())
		{
			fileToBeSaved.getParentFile().mkdirs();
		}
		OutputStream os = new FileOutputStream(fileToBeSaved);

		byte[] buffer = new byte[1024 * 1024];
		int bytesReaded = -1;
		InputStream is = multipartFile.getInputStream();
		while ((bytesReaded = is.read(buffer)) != -1)
		{
			os.write(buffer, 0, bytesReaded);
		}

		is.close();
		os.close();

		return true;
	}

	private String formatPath(String path)
	{
		if (StringUtils.isEmpty(path))
			return path;

		if (!path.startsWith("/"))
		{
			path = "/" + path;
		}
		if (path.endsWith("/"))
		{
			path = path.substring(0, path.length() - 1);
		}

		return path;
	}

	private String generateFileName(MultipartFile multipartFile)
	{
		if (multipartFile.isEmpty())
			return null;

		String uuid = UUID.randomUUID().toString();

		if ("image/jpeg".equals(multipartFile.getContentType()))
			return uuid + ".jpg";
		else if ("image/png".equals(multipartFile.getContentType()))
			return uuid + ".png";

		return null;
	}

	protected abstract String getUploadFolder();
}
