package org.lyk.main;

import java.io.File; 
import java.util.HashMap;
import java.util.Map;
 

//import org.springframework.web.servlet.DispatcherServlet;


class HttpServletRequest
{
}

interface MultipartRequest
{
	public Map<String,File> getFileMap();
}

class NoNameRequest extends HttpServletRequest implements MultipartRequest
{

	@Override
	public Map<String, File> getFileMap()
	{
		Map<String, File> retVal = new HashMap<String, File>();
		retVal.put("java", new File("//"));
		retVal.put("oracle", new File("//ff"));
		return retVal;
	}
}


public class Hello
{
	public static void main(String[] args) throws Exception
	{
		NoNameRequest noNameRequest = new NoNameRequest();
		func(noNameRequest);
		System.out.println("//main done");
	}
	
	public static void func(HttpServletRequest request)
	{
		System.out.println(request.getClass().getSimpleName());
	}
}
