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
		if (null == obj)
			return "null";

		if (this.visited.contains(obj))
			return "...";

		this.visited.add(obj);

		Class<?> cls = obj.getClass();
		if (cls == String.class)
			return (String) obj;

		if (cls.isArray())
		{
			String r = cls.getComponentType() + "[]{";
			for (int i = 0; i < Array.getLength(obj); i++)
			{
				if (i > 0)
					r += ",";
				Object val = Array.get(obj, i);
				if (cls.getComponentType().isPrimitive())
					r += val;
				else
					r += toString(val);
			}
			return r + "}";
		}

		String r = cls.getName();
		do
		{
			r += "[";
			Field[] fields = cls.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			for (Field f : fields)
			{
				// 非静态字段
				if (!Modifier.isStatic(f.getModifiers()))
				{
					if (!r.endsWith("["))
						r += ",";
					r += f.getName() + "=";
					try
					{
						Class<?> t = f.getType();
						Object val = f.get(obj);
						if (t.isPrimitive())
							r += val;
						else
							r += toString(val);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			r += "]";
			cls = cls.getSuperclass();
		} while (cls != null);

		return r;
	}
}
