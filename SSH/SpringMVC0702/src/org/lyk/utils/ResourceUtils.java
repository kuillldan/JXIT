package org.lyk.utils;

import java.util.Scanner;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

public class ResourceUtils
{
	private Resource resource;

	public Resource getResource()
	{
		return resource;
	}

	public void setResource(Resource resource)
	{
		this.resource = resource;
	}

	public void print() throws Exception
	{
		if (this.resource.exists())
		{
			Scanner scanner = new Scanner(this.resource.getInputStream());
			scanner.useDelimiter("\n");
			while (scanner.hasNext())
			{
				System.out.println(scanner.next());
			}
			scanner.close();
		}
		else
		{
			System.out.println("resource not found");
		}
	}
}
