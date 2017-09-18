package main;

import java.util.Arrays;

import equals.Book;
import equals.Employee; 

public class Hello
{
	public static void main(String[] args)
	{
		Employee e = new Employee();
		Book b = new Book();
		b.setName("JAVA鏍稿績鎶�鏈�");
		b.setOwner(e);
		
		e.setName("yuankui");
		e.setSalary(99.3);
		e.setBook(b);
		
		Object[] all = new Object[5];
		all[0] = 1;
		all[1] = "abc";
		System.out.println(all.getClass().getComponentType());
	}
}
