package other;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import utils.StringUtils;

public class BeanOperator
{
	private Object obj;
	private String name;
	private String value;
	private String[] arrayValue;

	public BeanOperator(Object realObject, String name, String value)
	{
		this.obj = realObject;
		this.name = name;
		this.value = value;
		this.bind();
	}
	
	public BeanOperator(Object realObject, String name, String[] value)
	{
		this.obj = realObject;
		this.name = name;
		this.arrayValue = value;
		
		this.bind();
	}

	public void bind()
	{
		try
		{
			String[] chain = this.name.split("\\.");
			for (int i = 0; i < chain.length - 1; i++)
			{
				Method getter = this.obj.getClass().getDeclaredMethod(
						"get" + StringUtils.initCap(chain[i]));
				this.obj = getter.invoke(this.obj);
			}

			Field filed = this.obj.getClass().getDeclaredField(
					chain[chain.length - 1]);
			Method setter = this.obj.getClass().getDeclaredMethod(
					"set" + StringUtils.initCap(chain[chain.length - 1]),
					filed.getType());
			String filedType = filed.getType().getSimpleName();
			
			if ("Integer".equalsIgnoreCase(filedType) || "int".equalsIgnoreCase(filedType))
			{
				if(this.value.matches("\\d+"))
				{
					setter.invoke(this.obj, Integer.parseInt(this.value));
				}
			} else if ("Double".equalsIgnoreCase(filedType) || "double".equalsIgnoreCase(filedType))
			{
				if(this.value.matches("\\d+(\\.\\d+)?"))
				{
					setter.invoke(this.obj, Double.parseDouble(this.value));
				}
			} else if ("Date".equalsIgnoreCase(filedType))
			{
				if(this.value.matches("\\d{4}-\\d{2}-\\d{2}"))
				{
					setter.invoke(this.obj,
							new SimpleDateFormat("yyyy-MM-dd").parse(this.value));
				}
			} else if ("String".equalsIgnoreCase(filedType))
			{
				setter.invoke(this.obj, this.value);
			}else if("Integer[]".equalsIgnoreCase(filedType))
			{
				setter.invoke(this.obj, new Object[]{this.arrayValue});
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
