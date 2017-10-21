package org.lyk.caoliu;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public static void main(String[] args)
	{
		try
		{
			URL url = new URL("http://www.baidu.com");
			URLConnection conn = url.openConnection();
			conn.getInputStream();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
