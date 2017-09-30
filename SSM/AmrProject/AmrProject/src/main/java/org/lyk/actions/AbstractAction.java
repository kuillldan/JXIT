package org.lyk.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;

import org.lyk.utils.CommonConstant;
import org.lyk.utils.StringUtils;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AbstractAction
{
	@Resource
	private MessageSource messageSource;

	private String getString(String key, Object... params)
	{
		return this.messageSource.getMessage(key, params, Locale.getDefault());
	}

	public String getMessage(String key, Object... params)
	{
		return this.getString(key, params);
	}

	public String getPage(String key)
	{
		return this.getString(key);
	}

	public String getValidation(String key)
	{
		return this.getString(key);
	}

	protected void setForwardMessageAndUrl(ModelAndView mav, String msg, String url)
	{
		mav.setViewName(this.getPage("forward.jsp"));
		mav.addObject("msg", msg);
		mav.addObject("url", url);
	}

	protected void setSystemError(ModelAndView mav, String msg, Exception e)
	{
		mav.setViewName(this.getPage(CommonConstant.ERROR_JSP));
		mav.addObject(CommonConstant.MSG, msg);
		CommonConstant.LOGGER.error(msg);
		CommonConstant.LOGGER.error(e.getMessage(), e);
	}

	protected String generatePhotoFileName(MultipartFile pic)
	{
		if (pic == null)
			return CommonConstant.NOPHOTO_JPG;

		if (pic.getSize() <= 0)
			return CommonConstant.NOPHOTO_JPG;

		String photoExt = this.getPhotoExt(pic.getContentType());
		if(StringUtils.isEmpty(photoExt))
			return CommonConstant.NOPHOTO_JPG;
		
		return UUID.randomUUID().toString() + "." + photoExt;
	}

	private String getPhotoExt(String contentType)
	{
		if ("image/bmp".equals(contentType))
		{
			return "bmp";
		} else if ("image/gif".equals(contentType))
		{
			return "gif";
		} else if ("image/jpeg".equals(contentType))
		{
			return "jpg";
		} else if ("image/png".equals(contentType))
		{
			return "png";
		}

		return null;
	}

	protected boolean savePhoto(MultipartFile photo, String photoFullPath)
	{
		if (photo == null || photoFullPath.endsWith(CommonConstant.NOPHOTO_JPG))
			return false;

		if (photo.getSize() <= 0 || CommonConstant.NOPHOTO_JPG.equalsIgnoreCase(photoFullPath))
			return false;

		InputStream is = null;
		OutputStream os = null;

		try
		{
			File photoFile = new File(photoFullPath);
			if (!photoFile.getParentFile().exists())
			{
				photoFile.getParentFile().mkdirs();
			}

			is = photo.getInputStream();
			os = new FileOutputStream(photoFile);

			byte[] buffer = new byte[CommonConstant.BUFFER_SIZE];
			Integer bytesReaded = -1;
			while ((bytesReaded = is.read(buffer, 0, CommonConstant.BUFFER_SIZE)) > -1)
			{
				os.write(buffer, 0, bytesReaded);
			}
		} catch (Exception e)
		{
			CommonConstant.LOGGER.warn("保存图片失败");
			CommonConstant.LOGGER.warn(e.getMessage(), e);
		} finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					CommonConstant.LOGGER.warn(e.getMessage(), e);
				}
			}

			if (os != null)
			{
				try
				{
					os.close();
				} catch (IOException e)
				{
					CommonConstant.LOGGER.warn(e.getMessage(), e);
				}
			}
		}

		return true;
	}
}