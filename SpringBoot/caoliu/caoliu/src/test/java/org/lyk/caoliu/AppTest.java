package org.lyk.caoliu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.io.InputStream;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.lyk.caoliu.vo.Post;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
{
	// http://www.t66y.com/thread0806.php?fid=22&search=&page=1
	public static void main(String[] args) throws Exception
	{
		// saveAllFile();
		// "C:\\D\\sources\\caoliu\\"
		for (int i = 1; i <= 10; i++)
		{
			List<Post> allPosts = showAll(i + ".txt");
			
			
		}
		System.out.println("done");
	}

	public static void saveAllFile() throws Exception
	{
		System.setProperty("proxyPort", "1080");
		System.setProperty("proxyHost", "127.0.0.1");
		System.setProperty("proxySet", "true");

		for (int i = 1; i <= 50; i++)
		{
			String sendRecvGet = GetPostTest.sendGet("http://www.t66y.com/thread0806.php", "fid=22&search=&page=" + i);
			OutputStream os = new FileOutputStream("C:\\D\\sources\\caoliu\\" + i + ".txt");
			os.write(sendRecvGet.getBytes());
			os.close();
			System.out.println("成功保存(" + i + ")个文件.");
		}

		// String sendRecvGet =
		// GetPostTest.sendGet("http://www.t66y.com/thread0806.php",
		// "fid=22&search=&page=2");
		// System.out.println(sendRecvGet);
	}

	public static List<Post> showAll(String fileName) throws Exception
	{
		List<Post> allPosts = new ArrayList<>();

		File caoliu = new File("C:\\D\\sources\\caoliu\\" + fileName);
		Scanner scanner = new Scanner(caoliu);
		StringBuffer sb = new StringBuffer();
		while (scanner.hasNextLine())
		{
			String newLine = scanner.nextLine();
			sb.append(newLine).append("\r\n");
			// if(newLine.matches("\t<td>\\d+</td>"))
			// {
			// System.out.println(newLine);
			// }
		}
		scanner.close();
		String content = sb.toString();
		Pattern pattern = Pattern.compile(
				"\t<h3><a href=\"htm_data/\\d*/\\d*/\\d*\\.html\" target=\"_blank\" id=\"\">.*</a></h3>  \r\n\r\n\t\r\n\r\n\t</td>\r\n\t<td>.*\r\n\t<td>\\d+</td>");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find())
		{
			Post post = new Post();

			String eachOne = matcher.group();
			String[] eachLine = eachOne.split("\r\n");

			String headLine = eachLine[0];

			// System.out.println(headLine);
			Matcher firstLineMatcher = Pattern.compile("\\d*/\\d*/\\d*\\.html").matcher(headLine);
			if (firstLineMatcher.find())
			{
				String html = firstLineMatcher.group();
				post.setHref("http://www.t66y.com/htm_data/" + html);
			}

			Matcher titleMatcher = Pattern.compile("id=\"\">.*\\[\\d+:\\d+\\]</a>").matcher(headLine);
			if (titleMatcher.find())
			{
				String title = titleMatcher.group();
				title = title.replaceAll("id=\"\">", "");
				title = title.replaceAll("</a>", "");
				post.setTitle(title);
			}

			String countLine = eachLine[6];
			countLine = countLine.replaceAll("\t", "");
			countLine = countLine.replaceAll("<td>", "");
			countLine = countLine.replaceAll("</td>", "");
			post.setCommentCount(Integer.parseInt(countLine));

			allPosts.add(post);
		}

		return allPosts;
	}
}
