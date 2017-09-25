package org.lyk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper
{
	public static String getString(Date date,String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static Date getDate(Integer yyyy, Integer MM, Integer dd) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder source = new StringBuilder();
		source.append(yyyy).append("-").append(MM).append("-").append(dd);
		return sdf.parse(source.toString());
	}

	public static Date getDate(Integer yyyy, Integer MM, Integer dd, Integer HH, Integer mm, Integer ss)
			throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder source = new StringBuilder();
		source.append(yyyy).append("-").append(MM).append("-").append(dd).append(" ").append(HH).append(":").append(mm)
				.append(":").append(ss);
		return sdf.parse(source.toString());
	}

	public static Date getDate(Integer yyyy, Integer MM, Integer dd, Integer HH, Integer mm, Integer ss, Integer SSS)
			throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder source = new StringBuilder();
		source.append(yyyy).append("-").append(MM).append("-").append(dd).append(" ").append(HH).append(":").append(mm)
				.append(":").append(ss).append(".").append(SSS);
		return sdf.parse(source.toString());
	}
}
