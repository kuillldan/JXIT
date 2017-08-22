package util;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.core.io.Resource;

public class ResourceUtil
{
	private Resource resource;
	
	public void setResource(Resource resource)
	{
		this.resource = resource;
	}
	
	public void print() throws IOException
	{
		if(this.resource != null)
		{
			Scanner scanner = new Scanner(this.resource.getInputStream());
			scanner.useDelimiter("\r\n");
			while(scanner.hasNext())
			{
				System.out.println(scanner.next());
			}
			scanner.close();
		}
		else
		{
			System.out.println("resouce is empty");
		}
	}
}
