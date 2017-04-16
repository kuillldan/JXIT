package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.faces.flow.builder.ReturnBuilder;

import utils.StringUtils;

public class BeanOperator
{
	private Object obj;
	private String propertyNames;
	private String value;
	private String[] arrayValue;

	public BeanOperator(Object obj, String propertyNames, String value) throws Exception
	{
		super();
		this.obj = obj;
		this.propertyNames = propertyNames;
		this.value = value;

	}

	public BeanOperator(Object obj, String properties, String[] arrayValue) throws Exception
	{
		super();
		this.obj = obj;
		this.propertyNames = properties;
		this.arrayValue = arrayValue;
	}

	public static Field getField(String propertyNames, Object obj) throws Exception
	{
		String[] allProperties = propertyNames.split("\\.");

		for (int i = 0; i < allProperties.length - 1; i++)
		{
			Method getter = obj.getClass().getMethod("get" + StringUtils.initCap(allProperties[i]));
			obj = getter.invoke(obj);
		}
		return obj.getClass().getDeclaredField(allProperties[allProperties.length - 1]);
	}

	public void validateProperties(Map<String, String> errors) throws Exception
	{
		String[] allProperties = this.propertyNames.split("\\.");

		for (int i = 0; i < allProperties.length - 1; i++)
		{
			Method getter = this.obj.getClass().getMethod("get" + StringUtils.initCap(allProperties[i]));
			this.obj = getter.invoke(obj);
		}
		Field field = obj.getClass().getDeclaredField(allProperties[allProperties.length - 1]);


		String fieldType = field.getType().getSimpleName(); 
		if (CONST.DATATYPE.String.getRealType().equalsIgnoreCase(fieldType))
		{
			// String类型
			if (StringUtils.isEmpty(this.value))
			{
				errors.put(this.propertyNames, "字符串类型字段" + this.propertyNames + "不能为空");
			}
		} else if (CONST.DATATYPE.Integer.getRealType().equalsIgnoreCase(fieldType)
				|| CONST.DATATYPE.Int.getRealType().equals(fieldType))
		{ 
			if (StringUtils.isEmpty(this.value))
			{
				errors.put(this.propertyNames, "整型字段" + this.propertyNames + "不能为空");
			} else
			{
				if (!StringUtils.validateRegex(this.value, "\\d+"))
				{
					errors.put(this.propertyNames, "整型字段" + this.propertyNames + "格式不合法");
				}
			}
		} else if (CONST.DATATYPE.Double.getRealType().equalsIgnoreCase(fieldType))
		{
			if (StringUtils.isEmpty(this.value))
			{
				errors.put(this.propertyNames, "小数类型字段" + this.propertyNames + "不能为空");
			} else
			{
				if (!StringUtils.validateRegex(this.value, "\\d+(\\.\\d+)?"))
				{
					errors.put(this.propertyNames, "小数类型字段" + this.propertyNames + "格式不合法");
				}
			}

		} else if (CONST.DATATYPE.DATE.getRealType().equalsIgnoreCase(fieldType))
		{
			if (StringUtils.isEmpty(this.value))
			{
				errors.put(this.propertyNames, "日期类型字段" + this.propertyNames + "不能为空");
			} else
			{
				if (!StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2}")
						&& !StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
						&& !StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
				{
					errors.put(this.propertyNames, "日期类型字段" + this.propertyNames + "格式不合法");
				}
			}
		} else if (CONST.DATATYPE.StringArray.getRealType().equalsIgnoreCase(fieldType))
		{
			if (this.arrayValue == null)
			{
				errors.put(this.propertyNames, "字符串类型数组" + this.propertyNames + "不能为空");
			}
		} else if (CONST.DATATYPE.IntArray.getRealType().equalsIgnoreCase(fieldType))
		{
			if (this.arrayValue == null)
			{
				errors.put(this.propertyNames, "整数类型数组" + this.propertyNames + "不能为空");
			} else
			{
				int[] values = new int[this.arrayValue.length];
				for (int i = 0; i < values.length; i++)
				{
					try
					{
						values[i] = Integer.parseInt(this.arrayValue[i]);
					} catch (Exception e)
					{
						errors.put(this.propertyNames, "整数类型的数组" + this.propertyNames + "格式不正确");
						break;
					}
				}
			}
		} else if (CONST.DATATYPE.IntegerArray.getRealType().equalsIgnoreCase(fieldType))
		{
			if (this.arrayValue == null)
			{
				errors.put(this.propertyNames, "整数类型数组" + this.propertyNames + "不能为空");
			} else
			{
				Integer[] values = new Integer[this.arrayValue.length];
				for (int i = 0; i < this.arrayValue.length; i++)
				{
					try
					{
						values[i] = Integer.parseInt(this.arrayValue[i]);
					} catch (Exception e)
					{
						errors.put(this.propertyNames, "整数类型的数组" + this.propertyNames + "格式不正确");
						break;
					}
				}
			}
		} else if (CONST.DATATYPE.doubleArray.getRealType().equals(fieldType))
		{
			if (this.arrayValue == null)
			{
				errors.put(this.propertyNames, "小数类型数组" + this.propertyNames + "不能为空");
			} else
			{
				double[] values = new double[this.arrayValue.length];
				for (int i = 0; i < this.arrayValue.length; i++)
				{
					try
					{
						values[i] = Double.parseDouble(this.arrayValue[i]);
					} catch (Exception e)
					{
						errors.put(this.propertyNames, "小数类型的数组" + this.propertyNames + "格式不正确");
						break;
					}
				}
			}
		} else if (CONST.DATATYPE.DoubleArray.getRealType().equals(fieldType))
		{
			if (this.arrayValue == null)
			{
				errors.put(this.propertyNames, "小数类型数组" + this.propertyNames + "不能为空");
			} else
			{
				Double[] values = new Double[this.arrayValue.length];
				for (int i = 0; i < this.arrayValue.length; i++)
				{
					try
					{
						values[i] = Double.parseDouble(this.arrayValue[i]);
					} catch (Exception e)
					{
						errors.put(this.propertyNames, "小数类型的数组" + this.propertyNames + "格式不正确");
						break;
					}
				}
			}
		} else
		{
			throw new Exception("unsupported data type: " + fieldType);
		}
	}

	public void handleProperties() throws Exception
	{
		String[] allProperties = this.propertyNames.split("\\.");

		for (int i = 0; i < allProperties.length - 1; i++)
		{
			Method getter = this.obj.getClass().getMethod("get" + StringUtils.initCap(allProperties[i]));
			this.obj = getter.invoke(obj);
		}
		Field field = obj.getClass().getDeclaredField(allProperties[allProperties.length - 1]);

		Method setter = this.obj.getClass().getMethod(
				"set" + StringUtils.initCap(allProperties[allProperties.length - 1]), field.getType());

		String fieldType = field.getType().getSimpleName();
		if (CONST.DATATYPE.String.getRealType().equalsIgnoreCase(fieldType))
		{
			setter.invoke(this.obj, this.value);
		} else if (CONST.DATATYPE.Integer.getRealType().equalsIgnoreCase(fieldType)
				|| CONST.DATATYPE.Int.getRealType().equals(fieldType))
		{
			if (StringUtils.validateRegex(this.value, "\\d+"))
			{
				setter.invoke(this.obj, Integer.parseInt(this.value));
			}
		} else if (CONST.DATATYPE.Double.getRealType().equalsIgnoreCase(fieldType))
		{
			if (StringUtils.validateRegex(this.value, "\\d+(\\.\\d+)?"))
			{
				setter.invoke(this.obj, Double.parseDouble(this.value));
			}

		} else if (CONST.DATATYPE.DATE.getRealType().equalsIgnoreCase(fieldType))
		{
			if (StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2}"))
			{
				setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd").parse(this.value));
			} else if (StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))
			{
				setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.value));
			} else if (StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
			{
				setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(this.value));
			}
		} else if (CONST.DATATYPE.StringArray.getRealType().equalsIgnoreCase(fieldType))
		{
			String[] values = this.arrayValue;
			setter.invoke(this.obj, new Object[] { values });
		} else if (CONST.DATATYPE.IntArray.getRealType().equalsIgnoreCase(fieldType))
		{
			int[] values = new int[this.arrayValue.length];
			for (int i = 0; i < values.length; i++)
			{
				values[i] = Integer.parseInt(this.arrayValue[i]);
			}
			setter.invoke(this.obj, new Object[] { values });
		} else if (CONST.DATATYPE.IntegerArray.getRealType().equalsIgnoreCase(fieldType))
		{
			Integer[] values = new Integer[this.arrayValue.length];
			for (int i = 0; i < this.arrayValue.length; i++)
			{
				values[i] = Integer.parseInt(this.arrayValue[i]);
			}
			setter.invoke(this.obj, new Object[] { values });
		} else if (CONST.DATATYPE.doubleArray.getRealType().equals(fieldType))
		{
			double[] values = new double[this.arrayValue.length];
			for (int i = 0; i < this.arrayValue.length; i++)
			{
				values[i] = Double.parseDouble(this.arrayValue[i]);
			}
			setter.invoke(this.obj, new Object[] { values });
		} else if (CONST.DATATYPE.DoubleArray.getRealType().equals(fieldType))
		{
			Double[] values = new Double[this.arrayValue.length];
			for (int i = 0; i < this.arrayValue.length; i++)
			{
				values[i] = Double.parseDouble(this.arrayValue[i]);
			}
			setter.invoke(this.obj, new Object[] { values });
		} else
		{
			throw new Exception("unsupported data type: " + fieldType);
		}
	}
}
