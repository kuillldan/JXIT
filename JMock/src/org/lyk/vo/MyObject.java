package org.lyk.vo;

public class MyObject
{
	public MyObject()
	{
		System.out.println("********构造函数嗲用***********");
	}
	public String hello(String name)
	{
		System.out.println("这到底是什么东西????");
		return "Hello " + name;
	}
}