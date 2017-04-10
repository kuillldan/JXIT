package org.lyk.utils;

public class StringUtils
{
	public static boolean isEmpty(String str)
	{
		if("".equals(str) || null == str)
			return true;
		else
			return false;
	}
	
	public static boolean isTheSame(String str1, String str2)
	{
		if(isEmpty(str1) || isEmpty(str2))
			return false;
		else
		{
			if(str1.equals(str2))
				return true;
			else
				return false;
		}
	}
}
