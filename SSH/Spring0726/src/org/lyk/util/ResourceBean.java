package org.lyk.util;
//org.lyk.util.ResourceBean
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.Resource;

public class ResourceBean
{
	private Resource resource;
	
	public void setResource(Resource resource)
	{
		this.resource = resource;
	}
	
	public InputStream getInputStream()
	{
		try
		{
			return this.resource.getInputStream();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
