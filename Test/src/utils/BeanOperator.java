package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

public class BeanOperator
{
	private Object rootObject;
	private String property;
	private Object value;

	private Object currentObject;
	private String lastFieldName;
	private Field lastField;

	public BeanOperator(Object rootObject, String property, Object value)
	{
		super();
		this.rootObject = rootObject;
		this.property = property;
		this.value = value;
	}

	public void handleString() throws Exception
	{
		String[] propertiesInArray = property.split("\\."); 
//		this.currentObject = this.rootObject.getClass().getDeclaredMethod("get" + StringUtils.initCap(propertiesInArray[0])).invoke(this.rootObject);
		this.currentObject = this.rootObject;
		for (int i = 0; i < propertiesInArray.length - 1; i++)
		{
			Method getter = this.currentObject.getClass().getDeclaredMethod("get" + StringUtils.initCap(propertiesInArray[i]));
			System.out.println("[debug]:" + getter.getName());
			this.currentObject = getter.invoke(this.currentObject);
		}
		this.lastFieldName = propertiesInArray[propertiesInArray.length - 1];
		this.lastField = this.currentObject.getClass()
				.getDeclaredField(propertiesInArray[propertiesInArray.length - 1]);
	}
 
	public void setValue() throws Exception
	{
		if (this.value == null)
			return;

		String lastFieldTypeName = this.lastField.getType().getSimpleName();
		Method setter = this.currentObject.getClass().getDeclaredMethod(
				"set" + StringUtils.initCap(this.lastFieldName), this.lastField.getType());

		if (!lastFieldTypeName.contains("[]"))
		{
			String valueInString = this.value.toString();
			if (StringUtils.isEmpty(valueInString))
				return;

			System.out.println("[debug]-lastFieldTypeName:" + lastFieldTypeName);
			if ("String".equalsIgnoreCase(lastFieldTypeName))
			{
				setter.invoke(this.currentObject, valueInString);
			} else if ("Integer".equalsIgnoreCase(lastFieldTypeName) || "int".equalsIgnoreCase(lastFieldTypeName))
			{
				System.out.println(setter.getName());
				setter.invoke(this.currentObject, Integer.parseInt(valueInString));
			} else if ("Double".equalsIgnoreCase(lastFieldTypeName) || "double".equalsIgnoreCase(lastFieldTypeName))
			{
				setter.invoke(this.currentObject, Double.parseDouble(valueInString));
			} else if ("Date".equalsIgnoreCase(lastFieldTypeName))
			{
				if (valueInString.matches("\\d{4}-\\d{2}-\\d{2}"))
				{
					setter.invoke(this.currentObject, new SimpleDateFormat("yyyy-MM-dd").parse(valueInString));
				} else if (valueInString.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))
				{
					setter.invoke(this.currentObject, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valueInString));
				} else if (valueInString.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
				{
					setter.invoke(this.currentObject, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(valueInString));
				}
			}
		} else
		{
			//处理数组
			String[] valueInStringArray = (String[])this.value;
			if(valueInStringArray.length == 0)
				return ;
			
			if("String[]".equalsIgnoreCase(lastFieldTypeName))
			{
				setter.invoke(this.currentObject, new Object[]{valueInStringArray});
			}else if("Integer[]".equalsIgnoreCase(lastFieldTypeName))
			{
				Integer[] acturalValuesInArray = new Integer[valueInStringArray.length];
				for(int i = 0; i < valueInStringArray.length; i++)
				{
					acturalValuesInArray[i] = Integer.parseInt(valueInStringArray[i]);
				}
				setter.invoke(this.currentObject, new Object[]{acturalValuesInArray});
			}else if("int[]".equalsIgnoreCase(lastFieldTypeName))
			{
				int[] acturalValuesInArray = new int[valueInStringArray.length];
				for(int i = 0; i < valueInStringArray.length; i++)
				{
					acturalValuesInArray[i] = Integer.parseInt(valueInStringArray[i]);
				}
				setter.invoke(this.currentObject, new Object[]{acturalValuesInArray});
			}else if("Double[]".equalsIgnoreCase(lastFieldTypeName))
			{
				Double[] acturalValuesInArray = new Double[valueInStringArray.length];
				for(int i = 0; i < valueInStringArray.length; i++)
				{
					acturalValuesInArray[i] = Double.parseDouble(valueInStringArray[i]);
				}
				setter.invoke(this.currentObject, new Object[]{acturalValuesInArray});
			}else if("double[]".equalsIgnoreCase(lastFieldTypeName))
			{
				double[] acturalValuesInArray = new double[valueInStringArray.length];
				for(int i = 0; i < valueInStringArray.length; i++)
				{
					acturalValuesInArray[i] = Double.parseDouble(valueInStringArray[i]);
				}
				setter.invoke(this.currentObject, new Object[]{acturalValuesInArray});
			}
		}
	}
}