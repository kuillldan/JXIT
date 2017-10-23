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
import java.util.Arrays;
import java.util.Collections;
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
		List<Post> all = showAll("1.txt");
		for(Post post:all)
		{
			System.out.println(post);
		}
	} 
	
	public static List<Post> showAll(String fileName) throws Exception
	{
		List<Post> allPosts = new ArrayList<>();

		File caoliu = new File("C:\\D\\JXIT\\temp\\caoliu\\" + fileName);
		Scanner scanner = new Scanner(caoliu);
		StringBuffer sb = new StringBuffer();
		while (scanner.hasNextLine())
		{
			String newLine = scanner.nextLine();
			sb.append(newLine).append("\r\n"); 
		}
		scanner.close();
		String content = sb.toString();
		Pattern pattern = Pattern.compile(
				"\t<h3><a href=\"htm_data/\\d*/\\d*/\\d*\\.html\" target=\"_blank\" id=\"\">.*</a></h3>  \r\n\r\n\t\r\n\r\n\t</td>\r\n\t<td>.*\r\n\t<td>\\d+</td>\r\n\t<td><a href=\".*</a><br />by: .*</td>");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find())
		{
			Post post = new Post();

			String group = matcher.group();
			
			System.out.println(group);
			System.out.println("================");
			
			
			String[] eachLine = group.split("\r\n");

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
			Integer commentCount = Integer.parseInt(countLine);
			post.setCommentCount(commentCount);
			
			String authorLine = eachLine[7];
			Matcher autherMatcher = Pattern.compile("by: .*</td>").matcher(authorLine);
			if(autherMatcher.find())
			{
				String author = autherMatcher.group();
				author = author.replaceAll("</td>", "");
				post.setAuthor(author);
			}

			if (commentCount >= 5)
				allPosts.add(post);
		}

		return allPosts;
	}
}
