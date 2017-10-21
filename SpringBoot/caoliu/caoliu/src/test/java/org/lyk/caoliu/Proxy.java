package org.lyk.caoliu;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class Proxy
{
	public Proxy()
	{
		System.setProperty("proxyPort", "1080");
		System.setProperty("proxyHost", "127.0.0.1");
		System.setProperty("proxySet", "true");
	}
	
	
}
