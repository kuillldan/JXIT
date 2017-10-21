package org.lyk.caoliu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GetPostTest
{
	public static String sendGet(String url, String param)
	{
		StringBuffer result = new StringBuffer();
		String urlName = url + "?" + param;
		try
		{
			URL realURL = new URL(urlName);
			URLConnection conn = realURL.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
			conn.connect();
//			Map<String, List<String>> map = conn.getHeaderFields();
//			for (String s : map.keySet())
//			{
//				System.out.println(s + "-->" + map.get(s));
//			}

			Scanner scanner = new Scanner(conn.getInputStream(), "gbk");

			while (scanner.hasNextLine())
			{
				result.append(scanner.nextLine()).append("\r\n");
			}

			scanner.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return result.toString();
	}

	public static String sendPost(String url, String param)
	{
		String result = "";
		try
		{
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
			// post设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null)
			{
				result += "\n" + line;
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
