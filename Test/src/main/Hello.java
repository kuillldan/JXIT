package main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		InputStream is = new FileInputStream(new File("C:\\Users\\liuyuank\\Pictures\\touxiang.jpg"));
		int tempByte = -1;
		while((tempByte = is.read()) != -1)
		{
			if(tempByte <= 0)
			{
				System.out.println(tempByte);
			}
		}
		is.close();
	}
}
