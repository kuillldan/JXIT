package org.lyk.Test0919;

import java.lang.reflect.Field;

import ch05.Student;
import ch08.Pair;

public class App
{
	public static void main(String[] args) throws Exception
	{ 
		min("ZZZ");
	}
	
	public static <T extends Comparable> void min(T a)
	{
		System.out.println(a);
	}
	public static void min(Comparable a)
	{
		System.out.println("no" + a);
	}
}
