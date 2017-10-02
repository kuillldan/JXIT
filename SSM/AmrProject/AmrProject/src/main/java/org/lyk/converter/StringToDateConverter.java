package org.lyk.converter;

import java.util.Date;

import org.lyk.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date>
{
	private static final Logger logger = LoggerFactory.getLogger(CommonConstant.LOGFILE);
	
	@Override
	public Date convert(String arg0)
	{
		logger.debug("*****转换器" + this.getClass().getSimpleName() + "执行");
		// TODO Auto-generated method stub
		return null;
	}

}
