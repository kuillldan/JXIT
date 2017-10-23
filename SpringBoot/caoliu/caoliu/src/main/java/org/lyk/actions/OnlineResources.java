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
			Integer max = 50;

			List<Post> entireSite = new ArrayList<>();
			for (int i = 1; i <= max; i++)
			{
				List<Post> allPosts = showAll(i + ".txt");
				System.out.println("读取文件:" + i + "成功");
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
			
//			System.out.println(group);
//			System.out.println("================");
			
			
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
