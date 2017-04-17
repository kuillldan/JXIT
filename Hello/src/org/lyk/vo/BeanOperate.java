package org.lyk.vo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import utils.StringUtils;

public class BeanOperate
{
	private Object obj;
	private String properties;
	private String value;
	private String[] arrayValue;

	public BeanOperate(Object obj, String properties, String value) throws Exception
	{
		super();
		this.obj = obj;
		this.properties = properties;
		this.value = value;
		this.handleProperties();

	}

	public BeanOperate(Object obj, String properties, String[] arrayValue) throws Exception
	{
		super();
		this.obj = obj;
		this.properties = properties;
		this.arrayValue = arrayValue;
		this.handleProperties();
	}

	public void handleProperties() throws Exception
	{
		String[] allProperties = this.properties.split("\\.");
		for (int i = 0; i < allProperties.length - 1; i++)
		{
			Method getter = this.obj.getClass().getMethod("get" + StringUtils.initCap(allProperties[i]));
			this.obj = getter.invoke(this.obj);
		}
		Field field = this.obj.getClass().getDeclaredField(allProperties[allProperties.length - 1]);
		Method setter = this.obj.getClass().getMethod(
				"set" + StringUtils.initCap(allProperties[allProperties.length - 1]), field.getType());

		String fieldType = field.getType().getSimpleName();
		if (Const.DATATYPE.String.getRealType().equalsIgnoreCase(fieldType))
		{
			setter.invoke(this.obj, this.value);
		} else if (Const.DATATYPE.Integer.getRealType().equalsIgnoreCase(fieldType)
				|| Const.DATATYPE.Int.getRealType().equals(fieldType))
		{
			if (StringUtils.validateRegex(this.value, "\\d+"))
			{
				setter.invoke(this.obj, Integer.parseInt(this.value));
			}
		} else if (Const.DATATYPE.Double.getRealType().equalsIgnoreCase(fieldType))
		{
			if (StringUtils.validateRegex(this.value, "\\d+(\\.\\d+)?"))
			{
				setter.invoke(this.obj, Double.parseDouble(this.value));
			}

		} else if (Const.DATATYPE.DATE.getRealType().equalsIgnoreCase(fieldType))
		{
			if (StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2}"))
			{
				setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd").parse(this.value));
			} else if (StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))
			{
				setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.value));
			} else if (StringUtils.validateRegex(this.value,
					"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
			{
				setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(this.value));
			}
		} else if (Const.DATATYPE.StringArray.getRealType().equalsIgnoreCase(fieldType))
		{
			String[] values = this.arrayValue;
			setter.invoke(this.obj, new Object[]
			{ values });
		} else if (Const.DATATYPE.IntArray.getRealType().equalsIgnoreCase(fieldType))
		{
			int[] values = new int[this.arrayValue.length];
			for (int i = 0; i < values.length; i++)
			{
				values[i] = Integer.parseInt(this.arrayValue[i]);
			}
			setter.invoke(this.obj, new Object[]
			{ values });
		} else if (Const.DATATYPE.IntegerArray.getRealType().equalsIgnoreCase(fieldType))
		{
			Integer[] values = new Integer[this.arrayValue.length];
			for (int i = 0; i < this.arrayValue.length; i++)
			{
				values[i] = Integer.parseInt(this.arrayValue[i]);
			}
			setter.invoke(this.obj, new Object[]
			{ values });
		} else if (Const.DATATYPE.doubleArray.getRealType().equals(fieldType))
		{
			double[] values = new double[this.arrayValue.length];
			for (int i = 0; i < this.arrayValue.length; i++)
			{
				values[i] = Double.parseDouble(this.arrayValue[i]);
			}
			setter.invoke(this.obj, new Object[]
			{ values });
		} else if (Const.DATATYPE.DoubleArray.getRealType().equals(fieldType))
		{
			Double[] values = new Double[this.arrayValue.length];
			for (int i = 0; i < this.arrayValue.length; i++)
			{
				values[i] = Double.parseDouble(this.arrayValue[i]);
			}
			setter.invoke(this.obj, new Object[]
			{ values });
		} else
		{
			throw new Exception("unsupported data type: " + fieldType);
		}
	}
}
