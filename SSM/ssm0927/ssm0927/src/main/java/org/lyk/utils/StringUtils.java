package org.lyk.utils;

public class StringUtils
{
	public static boolean isEmpty(String str)
	{
		if(null == str || "".equals(str))
			return true;
		else
			return false;
	}
}
