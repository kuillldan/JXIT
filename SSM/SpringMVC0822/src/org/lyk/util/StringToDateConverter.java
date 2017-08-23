package org.lyk.util;
//org.lyk.util.StringToDateConverter
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date>
{
	private static final Logger logger = LoggerFactory.getLogger(StringToDateConverter.class);
	
	@Override
	public Date convert(String dateInString)
	{
		if(dateInString == null && "".equals(dateInString))
		{
			logger.info("客户端提交的字符串为空.");
			return null;
		}
		else
		{
			try
			{
				SimpleDateFormat sdf = null;
				if(dateInString.matches("\\d{4}-\\d{2}-\\d{2}"))
				{
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				else if(dateInString.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))
				{
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				}else if(dateInString.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
				{
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				}
				else
				{
					logger.warn("传入的日期("+dateInString+")格式不正确.");
					return null;
				}
				
				return sdf.parse(dateInString);
			} catch (Exception e)
			{
				logger.error(e.getMessage(),e);
				return null;
			}
		}
	}

}
