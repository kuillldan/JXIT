package bitool.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageSource
{
	private ResourceBundle rb = ResourceBundle.getBundle("Messages");
	
	public String getString(String key)
	{
		return rb.getString(key);
	}
	
	public String getString(String key, Object ... params)
	{
		return MessageFormat.format(this.getString(key), params);
	}
}
