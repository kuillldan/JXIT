package test;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by daiwe on 2017/7/3.
 */
public class Hello
{
	public static String getRandomCharAndNumr(Integer length)
	{
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++)
		{
			boolean b = random.nextBoolean();
			if (b)
			{ // 字符串
				// int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
				str += (char) (65 + random.nextInt(26));// 取得大写字母
			} else
			{ // 数字
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}

	public static void main(String[] args)
	{

		System.out.println("C" + getRandomCharAndNumr(8));

	}
}
