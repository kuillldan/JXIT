package main;

import utils.StringEncoder;


interface IMessage
{
	void showMsg();
}



public class Hello
{
	public static void main(String[] args) throws Exception
	{
		System.out.println(StringEncoder.getMD5Base64Str("hello", "shit"));
	}
}
