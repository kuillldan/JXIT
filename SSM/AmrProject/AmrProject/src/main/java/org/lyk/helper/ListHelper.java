package org.lyk.helper;

import java.util.List;

public class ListHelper
{
	public static void printList(List<?> allItems)
	{
		for(Object item : allItems)
		{
			System.out.println(item);
		}
	}
}
