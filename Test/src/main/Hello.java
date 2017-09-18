package main;

import java.util.Arrays;

import equals.Book;
import equals.Employee; 

class A{}
class B extends A{}
class C extends B{}


public class Hello
{
	public static void main(String[] args)
	{
		A[] all = new C[2];
		B[] allB = (B[])all;
		System.out.println(allB.getClass().getComponentType());
	}
}
