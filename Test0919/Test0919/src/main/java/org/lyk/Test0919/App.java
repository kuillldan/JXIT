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
		Employee e = new Employee("sheldon", 22.3, 1987, 11, 19);
		Manager m = new Manager("sheldon", 22.3, 1987, 11, 19);
		
		System.out.println(e.equals(m));
		System.out.println(m.equals(e));
	}
}
