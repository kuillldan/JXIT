package main;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import interfaces.Employee;

public class Hello
{
	public static void main(String[] args) throws NoSuchFieldException, SecurityException
	{
		Comparator.comparing(Employee::getName);
		
		Function<Employee, String> f = Employee::aaa;
		
		f.apply(new Employee("", null));
	}

	public String aaa()
	{
		return "xxx";
	}

	public static void show()
	{
		System.out.println("hello shit");
	}

	public static void print(Integer i)
	{

		String[] all = new String[3];
		all[0] = "hello";
		all[1] = "how";
		all[2] = "are";

	}
}
