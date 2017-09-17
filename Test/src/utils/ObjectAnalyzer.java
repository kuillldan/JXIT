package utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.I2F;

public class ObjectAnalyzer
{
	private ArrayList<Object> visited = new ArrayList<>();

	public String toString(Object obj)
	{
		if (obj == null)
			return "null";

		if (this.visited.contains(obj))
			return "...";

		this.visited.add(obj);

		Class<?> cls = obj.getClass();

		if (cls.isArray())
		{
			String r = cls.getComponentType() + "[]{";
			for (int i = 0; i < Array.getLength(obj); i++)
			{
				if (i > 0)
					r += ",";
				if(cls.getComponentType().isPrimitive())
				{
					r += Array.get(obj, i);
				}
				else
				{
					toString(Array.get(obj, i));
				}
			}
			r += "}";
			return r;
		}
		else
		{
			String r = cls.getName();
			r += "[";
			while(cls != null)
			{
				Field[] allFields = cls.getDeclaredFields();
				for(int i=0; i < allFields.length; i++)
				{
					if(!Modifier.isStatic(f.getModifiers()))
					{
						if(f.getType().isPrimitive())
						{ 
							
						}
					}
				}
				
				cls = cls.getSuperclass();
			}
			r += "]";
			return r;
		} 
	}
}
