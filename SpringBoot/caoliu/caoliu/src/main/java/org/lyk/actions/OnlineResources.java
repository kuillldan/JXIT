package org.lyk.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lyk.caoliu.vo.Post;
import org.lyk.entities.HttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OnlineResources extends AbstractController
{
	@RequestMapping("/showList")
	public String showList(Model model)
	{
		try
		{
			Integer max = 10;

			List<Post> entireSite = new ArrayList<>();
			for (int i = 1; i <= max; i++)
			{
				List<Post> allPosts = showAll(i + ".txt");
				entireSite.addAll(allPosts);
			}
			
			Collections.sort(entireSite);
//			for (Post post : entireSite)
//				System.out.println(post);

//			System.out.println("done");

			model.addAttribute("allItems", entireSite);
			return "show_online_resources";
		}
		catch(Exception e)
		{
			return "error";
		}
	}

	public static void saveAllFile(Integer max) throws Exception
	{
		System.setProperty("proxyPort", "8080");
		System.setProperty("proxyHost", "web-proxy.jp.hpecorp.net");
		System.setProperty("proxySet", "true");

		for (int i = 1; i <= max; i++)
		{
			String sendRecvGet = HttpClient.sendGet("http://www.t66y.com/thread0806.php", "fid=22&search=&page=" + i);
			OutputStream os = new FileOutputStream("C:\\D\\JXIT\\temp\\caoliu\\" + i + ".txt");
			os.write(sendRecvGet.getBytes());
			os.close();
			System.out.println("成功保存(" + i + ")个文件. 源:http://www.t66y.com/thread0806.php?fid=22&search=&page=" + i);
		}
	}

	private List<Post> showAll(String fileName) throws Exception
	{
		List<Post> allPosts = new ArrayList<>();

		File caoliu = new File("C:\\D\\JXIT\\temp\\caoliu\\" + fileName);
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
			Integer commentCount = Integer.parseInt(countLine);
			post.setCommentCount(commentCount);

			if (commentCount >= 5)
				allPosts.add(post);
		}

		return allPosts;
	}
}
