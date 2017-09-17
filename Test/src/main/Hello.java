package main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import dbc.DatabaseConnection;

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		Set<String> allHashCode = new HashSet<>();
		List<Employee> allEmployees = new LinkedList<>();
		for (int i = 1; i <= 1000000; i++)
		{
			Employee e = new Employee("sheldon", 2222.3, 1987, 10, 12);
			allEmployees.add(e);
			allHashCode.add(String.valueOf(e.hashCode()));
		}
		System.out.println(allHashCode.size());
		System.out.println(allEmployees.size());
//		System.out.println(allHashCode);
	}

	public static void func(String... strs)
	{
		System.out.println(strs.length);
	}
}
