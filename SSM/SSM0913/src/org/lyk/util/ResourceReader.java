package org.lyk.util;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

public class ResourceReader
{
	private static Logger logger = LoggerFactory.getLogger();
	
	public static InputStream getInputStream(String path)
	{
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		try
		{
			return resourceLoader.getResource(path).getInputStream();
		} catch (IOException e)
		{
			logger.error("读取文件("+path+")失败.");
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	public static void close(InputStream is)
	{
		if(is != null)
		{
			try
			{
				is.close();
			} catch (IOException e)
			{
				logger.error("关闭资源失败");
				logger.error(e.getMessage(),e);
			}
		}
		else
		{
			logger.warn("资源为空,无法关闭资源.");
		}
	}
}
