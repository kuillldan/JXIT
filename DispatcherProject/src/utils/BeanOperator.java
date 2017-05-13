package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class BeanOperator
{
	private Object rootObject;
	private String property;
	private Object value;

	private Object currentObject;
	private String lastFieldName;
	private Field lastField;

	public BeanOperator(Object rootObject, String property)
	{
		super();
		this.rootObject = rootObject;
		this.property = property;
	}

	public Object getCurrentObject()
	{
		return currentObject;
	}

	public void setCurrentObject(Object currentObject)
	{
		this.currentObject = currentObject;
	}

	public String getLastFieldName()
	{
		return lastFieldName;
	}

	public void setLastFieldName(String lastFieldName)
	{
		this.lastFieldName = lastFieldName;
	}

	public Field getLastField()
	{
		return lastField;
	}

	public void setLastField(Field lastField)
	{
		this.lastField = lastField;
	}

	public void handleString() throws Exception
	{
		String[] propertiesInArray = property.split("\\.");
		this.currentObject = this.rootObject;
		for (int i = 0; i < propertiesInArray.length - 1; i++)
		{
			Method getter = this.currentObject.getClass().getDeclaredMethod(
					"get" + StringUtils.initCap(propertiesInArray[i]));
			this.currentObject = getter.invoke(this.currentObject);
		}
		this.lastFieldName = propertiesInArray[propertiesInArray.length - 1];
		this.lastField = this.currentObject.getClass()
				.getDeclaredField(propertiesInArray[propertiesInArray.length - 1]);
	}

	public String validateValue(Object value) throws Exception
	{
		String errorMessage = null;
		this.value = value;
		if (this.value == null)
		{
			errorMessage = "值不能为空";
			return errorMessage;
		}

		String lastFieldTypeName = this.lastField.getType().getSimpleName();
		try
		{
			if (!lastFieldTypeName.contains("[]"))
			{
				String valueInString = this.value.toString();
				if (StringUtils.isEmpty(valueInString))
				{
					errorMessage = "值不能为空"; 
					return errorMessage;
				}

				if ("Integer".equalsIgnoreCase(lastFieldTypeName) || "int".equalsIgnoreCase(lastFieldTypeName))
				{
					try
					{
						Integer.parseInt(valueInString);
					} catch (NumberFormatException e)
					{
						errorMessage = "数字格式不正确"; 
					}
				} else if ("Double".equalsIgnoreCase(lastFieldTypeName) || "double".equalsIgnoreCase(lastFieldTypeName))
				{
					try
					{
						Double.parseDouble(valueInString);
					} catch (Exception e)
					{
						errorMessage = "小数类型格式不正确";
						 
					}
				} else if ("Date".equalsIgnoreCase(lastFieldTypeName))
				{
					if (!valueInString.matches("\\d{4}-\\d{2}-\\d{2}")
							&& !valueInString.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
							&& !valueInString.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
					{
						errorMessage = "日期格式不正确";
						 
					}
				}
				return errorMessage;
			} else
			{
				String[] valueInStringArray = (String[]) this.value;
				if (valueInStringArray.length == 0)
				{
					errorMessage = "数组值不能为空";
					return errorMessage;
				}
				if ("Integer[]".equalsIgnoreCase(lastFieldTypeName) || "int[]".equalsIgnoreCase(lastFieldTypeName))
				{
					try
					{
						for (int i = 0; i < valueInStringArray.length; i++)
						{
							Integer.parseInt(valueInStringArray[i]);
						}
					} catch (NumberFormatException e)
					{
						errorMessage = "整数数组格式不正确"; 
					}

				}  else if ("Double[]".equalsIgnoreCase(lastFieldTypeName) || "double[]".equalsIgnoreCase(lastFieldTypeName))
				{ 
					try
					{
						for (int i = 0; i < valueInStringArray.length; i++)
						{
							Double.parseDouble(valueInStringArray[i]);
						} 
					}
					catch(Exception e)
					{
						errorMessage = "小数数组格式不正确";
					}
				}
			}
			return errorMessage;
		} catch (Exception e)
		{
			errorMessage = "未知错误，请联系管理员";
			return errorMessage;
		} 
	}

	public void setValue(Object value) throws Exception
	{
		this.value = value;
		if (this.value == null)
			return;

		String lastFieldTypeName = this.lastField.getType().getSimpleName();
		Method setter = this.currentObject.getClass().getDeclaredMethod(
				"set" + StringUtils.initCap(this.lastFieldName), this.lastField.getType());

		try
		{
			if (!lastFieldTypeName.contains("[]"))
			{
				String valueInString = this.value.toString();
				if (StringUtils.isEmpty(valueInString))
					return;

				if ("String".equalsIgnoreCase(lastFieldTypeName))
				{
					setter.invoke(this.currentObject, valueInString);
				} else if ("Integer".equalsIgnoreCase(lastFieldTypeName) || "int".equalsIgnoreCase(lastFieldTypeName))
				{
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
						setter.invoke(this.currentObject,
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valueInString));
					} else if (valueInString.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
					{
						setter.invoke(this.currentObject,
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(valueInString));
					}
				}
			} else
			{
				// 处理数组
				String[] valueInStringArray = (String[]) this.value;
				if (valueInStringArray.length == 0)
					return;

				if ("String[]".equalsIgnoreCase(lastFieldTypeName))
				{
					setter.invoke(this.currentObject, new Object[] { valueInStringArray });
				} else if ("Integer[]".equalsIgnoreCase(lastFieldTypeName))
				{
					Integer[] acturalValuesInArray = new Integer[valueInStringArray.length];
					for (int i = 0; i < valueInStringArray.length; i++)
					{
						acturalValuesInArray[i] = Integer.parseInt(valueInStringArray[i]);
					}
					setter.invoke(this.currentObject, new Object[] { acturalValuesInArray });
				} else if ("int[]".equalsIgnoreCase(lastFieldTypeName))
				{
					int[] acturalValuesInArray = new int[valueInStringArray.length];
					for (int i = 0; i < valueInStringArray.length; i++)
					{
						acturalValuesInArray[i] = Integer.parseInt(valueInStringArray[i]);
					}
					setter.invoke(this.currentObject, new Object[] { acturalValuesInArray });
				} else if ("Double[]".equalsIgnoreCase(lastFieldTypeName))
				{
					Double[] acturalValuesInArray = new Double[valueInStringArray.length];
					for (int i = 0; i < valueInStringArray.length; i++)
					{
						acturalValuesInArray[i] = Double.parseDouble(valueInStringArray[i]);
					}
					setter.invoke(this.currentObject, new Object[] { acturalValuesInArray });
				} else if ("double[]".equalsIgnoreCase(lastFieldTypeName))
				{
					double[] acturalValuesInArray = new double[valueInStringArray.length];
					for (int i = 0; i < valueInStringArray.length; i++)
					{
						acturalValuesInArray[i] = Double.parseDouble(valueInStringArray[i]);
					}
					setter.invoke(this.currentObject, new Object[] { acturalValuesInArray });
				}
			}
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
	}
}