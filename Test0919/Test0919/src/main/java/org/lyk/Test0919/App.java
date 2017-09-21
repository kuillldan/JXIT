package org.lyk.Test0919;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import ch08.Employee;
import ch08.Pair;

public class App
{
	public static void main(String[] args) throws Exception
	{ 
//		List<Employee> allEmployeesInList = new ArrayList<>();
		Set<Integer> allHashCode = new HashSet<>();
		Set<Employee> allEmployeesInSet = new HashSet<>();
		 
		for(int i = 1; i <= 100; i++)
		{
			Employee e = new Employee();
			allHashCode.add(e.hashCode());
			allEmployeesInSet.add(e);
		}
		System.out.println(allHashCode.size());
		System.out.println(allEmployeesInSet.size());
	}
	
	public static void show(Pair<? super String> p)
	{
		p.setFirst("CCC");
		System.out.println(p.getFirst() + "," + p.getSecond());
	}
}
