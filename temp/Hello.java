import java.io.File;
import java.util.Scanner;

class StringUtils
{
	public static String initCap(String str)
	{
		if(null == str || "".equals(str))
			return str;
		
		if(str.length() == 1)
			return str.toUpperCase();
		
		return str.substring(0,1).toUpperCase() + str.substring(1);
	} 
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str)
	{
		if("".equals(str) || null == str)
			return true;
		else
			return false;
	}
	
	
	/**
	 * 验证两个字符串是否相同,不区分大小写
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isTheSame(String str1, String str2)
	{
		if(isEmpty(str1) || isEmpty(str2))
			return false;
		else
		{
			if(str1.equalsIgnoreCase(str2))
				return true;
			else
				return false;
		}
	}
	
	/**
	 * 通过正则表达式验证字符串
	 * @param data
	 * @param regex
	 * @return
	 */
	public static boolean validateRegex(String data, String regex)
	{
		if(isEmpty(data))
			return false;
		
		return data.matches(regex);
	}
}

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 1)
		{
			System.exit(1);
			return;
		}
		Scanner scanner = new Scanner(new File(args[0]));
		while (scanner.hasNextLine())
		{
			String newLine = scanner.nextLine();
			if (StringUtils.isEmpty(newLine) || newLine.length() <= 0)
			{
				continue;
			}
			System.out.println(newLine);
		}
		scanner.close();
		System.out.println("//Main done");
	}
}