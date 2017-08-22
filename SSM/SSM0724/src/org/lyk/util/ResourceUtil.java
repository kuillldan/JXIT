package org.lyk.util;

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
	
	public void print()
	{
		if(this.resource != null)
		{
			try
			{
				Scanner scanner = new Scanner(this.resource.getInputStream());
				scanner.useDelimiter("\r\n");
				while(scanner.hasNext())
				{
					System.out.println(scanner.next());
				}
				scanner.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			System.out.println("Resource is null");
		}
	}
	
}
