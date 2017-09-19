package main;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
 

import interfaces.Employee;
import interfaces.Manager; 

public class Hello
{
	public static void main(String[] args) throws NoSuchFieldException, SecurityException
	{
		Manager[] allManagers = new Manager[10];
		Employee[] allEmployees = allManagers;
		allEmployees[0] = new Manager("sheldo", 22.3, 22.3);
		System.out.println(Arrays.toString(allManagers));
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
