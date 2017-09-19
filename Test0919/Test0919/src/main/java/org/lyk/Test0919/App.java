package org.lyk.Test0919;

import java.util.Arrays;
import java.util.Objects;

import ch05.Employee;
import ch05.Manager;
import ch05.Person;
import ch05.Student;


/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args)
	{ 
		Manager m2 = new Manager("sheldon", 22.2, 4, 4, 5); 
		m2.setBouns(66.36);
		System.out.println(m2.toString());
	}
}
