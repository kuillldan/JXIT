import java.io.File;
import java.util.Scanner;

class Demo
{
	public static String info = null;
	static
	{
		info = "Hello";
		System.out.println("≥ı ºªØinfo");
	}
}

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		Class.forName("Demo");		
		System.out.println("//Main done");
	}
}