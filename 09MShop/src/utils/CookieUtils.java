package utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils
{
	public static void save(HttpServletResponse response, Integer expiry,String name, String value)
	{
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(expiry);
		cookie.setPath("/");
		
		response.addCookie(cookie);
	}
	
	public static Map<String, String> load(HttpServletRequest request)
	{
		Map<String, String> cookies = new HashMap<String, String>();
		
		Cookie[] allCookies = request.getCookies();
		if(allCookies != null)
		{
			for(Cookie cookie : allCookies)
			{
				cookies.put(cookie.getName(), cookie.getValue());
			}
		}
		
		return cookies;
	}
	
	public static void clear(HttpServletRequest request, HttpServletResponse response)
	{
		Map<String, String> cookies = CookieUtils.load(request);
		Set<Map.Entry<String,String>> entrySet = cookies.entrySet();
		if(entrySet != null)
		{
			Iterator<Map.Entry<String, String>> iter = entrySet.iterator();
			while(iter.hasNext())
			{
				Map.Entry<String, String> keyValue = iter.next();
				Cookie cookie = new Cookie(keyValue.getKey(), keyValue.getValue());
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
	}
}
