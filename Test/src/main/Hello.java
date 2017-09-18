package main;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hello
{
	public static void main(String[] args)
	{ 
		List<String> all = new ArrayList<>();
		Type t = all.getClass().getGenericSuperclass();
		Type types = ((ParameterizedType)t).getActualTypeArguments()[0];
		System.out.println((Class<?>)types);
//		all.add("Hello");
//		all.add("shit");
//		String[] newAll = (String[])toArray(all);
//		System.out.println(Arrays.toString(newAll));
	}
	
	
	public static <T> T[] toArray(List<?> a)
	{
		if(a == null)
			return null;
		if(a.size() == 0)
			return null;
		
		System.out.println(a.getClass());
		
		return null;
	}
	
	public static Object goodCopy(Object a, int newLength)
	{
		Class<?> componentType = a.getClass().getComponentType();
		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(a, 0, newArray, 0, Math.min(Array.getLength(a), newLength));
		return newArray;
	}
	
	public static Object[] badCopy(Object[] a, int newLength)
	{
		Object[] newArray = new Object[newLength];
		System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
		return newArray;
	}
}
