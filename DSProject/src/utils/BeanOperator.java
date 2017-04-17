package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;

public class BeanOperator
{
	private Object servletObject;

	public BeanOperator(Object servletObject)
	{
		super();
		this.servletObject = servletObject;
	}

	public void setValueAutomatic(HttpServletRequest request, SmartRequest smartRequest, boolean isEncryped)
	{
		Enumeration<String> allParameterNames = request.getParameterNames();
		while (allParameterNames.hasMoreElements())
		{
			String eachParameterName = allParameterNames.nextElement();
			String eachParameterValue = null;
			String[] eachParameterValues = null;
			Field lastField = this.getLastField(eachParameterName);
			lastField.setAccessible(true);
			String lastFieldTypeName = lastField.getType().getSimpleName();

			try
			{
				if (!lastFieldTypeName.contains("[]"))
				{
					eachParameterValue = request.getParameter(eachParameterName);
					if (CONST.DATATYPE.Int.getRealType().equalsIgnoreCase(lastFieldTypeName)
							|| CONST.DATATYPE.Integer.getRealType().equalsIgnoreCase(lastFieldTypeName))
					{
						if (!StringUtils.isEmpty(eachParameterValue)
								&& StringUtils.validateRegex(eachParameterValue, "\\d+"))
						{
							lastField.set(this.servletObject, Integer.parseInt(eachParameterValue));
						}
					} else if (CONST.DATATYPE.Double.getRealType().equalsIgnoreCase(lastFieldTypeName))
					{
						if (!StringUtils.isEmpty(eachParameterValue)
								&& StringUtils.validateRegex(eachParameterValue, "\\d+(\\.\\d+)?"))
						{
							lastField.set(this.servletObject, Double.parseDouble(eachParameterValue));
						}
					} else if (CONST.DATATYPE.DATE.getRealType().equalsIgnoreCase(lastFieldTypeName))
					{
						if (!StringUtils.isEmpty(eachParameterValue)
								&& (StringUtils.validateRegex(eachParameterValue, "\\d{4}-\\d{2}-\\d{2}")
										|| StringUtils.validateRegex(eachParameterValue,
												"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") || StringUtils
											.validateRegex(eachParameterValue,
													"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3}")))
						{
							lastField.set(this.servletObject,
									new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(eachParameterValue));
						}
					} else if (CONST.DATATYPE.String.getRealType().equalsIgnoreCase(lastFieldTypeName))
					{
						if (!StringUtils.isEmpty(eachParameterValue))
						{
							lastField.set(this.servletObject, eachParameterValue);
						}
					}
					else
					{
						System.out.println("******不支持的数据类型，无法完成自动赋值******");
					}
				} else
				{
					// 数组
					eachParameterValues = request.getParameterValues(eachParameterName);
					if (eachParameterName != null)
					{
						if (CONST.DATATYPE.IntArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
						{

							boolean arrayDataValid = true;
							int[] acturalValues = new int[eachParameterValues.length];
							for (int i = 0; i < eachParameterValues.length; i++)
							{
								try
								{
									acturalValues[i] = Integer.parseInt(eachParameterValues[i]);
								} catch (Exception e)
								{
									arrayDataValid = false;
									break;
								}
							}

							if (arrayDataValid == true)
							{
								lastField.set(this.servletObject, new Object[]
								{ acturalValues });
							}
						} else if (CONST.DATATYPE.IntegerArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
						{

							boolean arrayDataValid = true;
							Integer[] acturalValues = new Integer[eachParameterValues.length];
							for (int i = 0; i < eachParameterValues.length; i++)
							{
								try
								{
									acturalValues[i] = Integer.parseInt(eachParameterValues[i]);
								} catch (Exception e)
								{
									arrayDataValid = false;
									break;
								}
							}

							if (arrayDataValid == true)
							{
								lastField.set(this.servletObject, new Object[]
								{ acturalValues });
							}
						}else if (CONST.DATATYPE.DoubleArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
						{

							boolean arrayDataValid = true;
							Double[] acturalValues = new Double[eachParameterValues.length];
							for (int i = 0; i < eachParameterValues.length; i++)
							{
								try
								{
									acturalValues[i] = Double.parseDouble(eachParameterValues[i]);
								} catch (Exception e)
								{
									arrayDataValid = false;
									break;
								}
							}

							if (arrayDataValid == true)
							{
								lastField.set(this.servletObject, new Object[]
								{ acturalValues });
							}
						}
						else if (CONST.DATATYPE.doubleArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
						{

							boolean arrayDataValid = true;
							double[] acturalValues = new double[eachParameterValues.length];
							for (int i = 0; i < eachParameterValues.length; i++)
							{
								try
								{
									acturalValues[i] = Double.parseDouble(eachParameterValues[i]);
								} catch (Exception e)
								{
									arrayDataValid = false;
									break;
								}
							}

							if (arrayDataValid == true)
							{
								lastField.set(this.servletObject, new Object[]
								{ acturalValues });
							}
						}
						else if (CONST.DATATYPE.StringArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
						{ 
							String[] acturalValues = new String[eachParameterValues.length];
							for (int i = 0; i < eachParameterValues.length; i++)
							{
								acturalValues[i] = eachParameterValues[i];
							} 
						}
						else
						{
							System.out.println("******不支持的数据类型，无法完成数组的自动赋值******");
						}
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException | ParseException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void validateParameters(Map<String, String> errors, String status, HttpServletRequest request,
			SmartRequest smartRequest, boolean isEncryped)
	{
		String ruleName = status + "Validation";
		Field ruleFieldString;
		String parameters = null;
		try
		{
			ruleFieldString = this.servletObject.getClass().getDeclaredField(ruleName);

		} catch (NoSuchFieldException | SecurityException e)
		{
			// 在具体的Servlet中未找到相应验证规则
			return;
		}

		ruleFieldString.setAccessible(true);
		try
		{
			parameters = (String) ruleFieldString.get(this.servletObject);
			String[] allProperties = parameters.split("\\|");

			for (String property : allProperties)
			{
				Field lastField = this.getLastField(property);
				String lastFieldTypeName = lastField.getType().getSimpleName();
				String lastFieldName = lastField.getName();

				if (!isEncryped)
				{
					this.validateParameterInternal(errors, lastFieldTypeName, property, request);
				} else
				{
					this.validateParameterInternal(errors, lastFieldTypeName, property, smartRequest);
				}
			}

		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
		}

	}

	private void validateParameterInternal(Map<String, String> errors, String lastFieldTypeName, String property,
			HttpServletRequest request)
	{
		if (CONST.DATATYPE.Int.getRealType().equalsIgnoreCase(lastFieldTypeName)
				|| CONST.DATATYPE.Integer.getRealType().equals(lastFieldTypeName))
		{
			// 验证整型数据
			String lastFieldValue = request.getParameter(property);
			if (StringUtils.isEmpty(lastFieldValue))
			{
				errors.put(property, "整型数据(" + property + ")不能为空");
			} else if (!StringUtils.validateRegex(lastFieldValue, "\\d+"))
			{
				errors.put(property, "整型数据(" + property + ")格式不正确");
			}
		} else if (CONST.DATATYPE.Double.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			// 验证小数数据
			String lastFieldValue = request.getParameter(property);
			if (StringUtils.isEmpty(lastFieldValue))
			{
				errors.put(property, "小数数据(" + property + ")不能为空");
			} else if (!StringUtils.validateRegex(lastFieldValue, "\\d+(\\.\\d+)?"))
			{
				errors.put(property, "小数数据(" + property + ")格式不正确");
			}
		} else if (CONST.DATATYPE.DATE.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			// 验证日期数据
			String lastFieldValue = request.getParameter(property);
			if (StringUtils.isEmpty(lastFieldValue))
			{
				errors.put(property, "日期数据(" + property + ")不能为空");
			} else if (!StringUtils.validateRegex(lastFieldValue, "\\d{4}-\\d{2}-\\d{2}")
					&& !StringUtils.validateRegex(lastFieldValue, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
					&& !StringUtils.validateRegex(lastFieldValue, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
			{
				errors.put(property, "日期数据(" + property + ")格式不正确");
			}
		} else if (CONST.DATATYPE.String.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			String lastFieldValue = request.getParameter(property);
			if (StringUtils.isEmpty(lastFieldValue))
			{
				errors.put(property, "字符串数据(" + property + ")不能为空");
			}
		} else if (CONST.DATATYPE.IntArray.getRealType().equalsIgnoreCase(lastFieldTypeName)
				|| CONST.DATATYPE.IntegerArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			String[] lastFieldValue = request.getParameterValues(property);
			if (lastFieldValue == null)
			{
				errors.put(property, "整形数组(" + property + ")不能为空");
			} else
			{
				for (String value : lastFieldValue)
				{
					try
					{
						Integer.parseInt(value);
					} catch (Exception e)
					{
						errors.put(property, "整形数组(" + property + ")格式不正确");
						break;
					}
				}
			}
		} else if (CONST.DATATYPE.DoubleArray.getRealType().equalsIgnoreCase(lastFieldTypeName)
				|| CONST.DATATYPE.doubleArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			String[] lastFieldValue = request.getParameterValues(property);
			if (lastFieldValue == null)
			{
				errors.put(property, "小数数组(" + property + ")不能为空");
			} else
			{
				for (String value : lastFieldValue)
				{
					try
					{
						Double.parseDouble(value);
					} catch (Exception e)
					{
						errors.put(property, "小数数组(" + property + ")格式不正确");
						break;
					}
				}
			}
		} else if (CONST.DATATYPE.StringArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			String[] lastFieldValue = request.getParameterValues(property);
			if (lastFieldValue == null)
			{
				errors.put(property, "字符串数组(" + property + ")不能为空");
			}
		} else
		{
			System.out.println("****不支持的数据类型，无法完成数据校验****");
		}
	}

	private void validateParameterInternal(Map<String, String> errors, String lastFieldTypeName, String lastFieldName,
			SmartRequest request)
	{
		if (CONST.DATATYPE.Int.getRealType().equalsIgnoreCase(lastFieldTypeName)
				|| CONST.DATATYPE.Integer.getRealType().equals(lastFieldTypeName))
		{
			// 验证整型数据
			String lastFieldValue = request.getParameter(lastFieldName);
			if (StringUtils.isEmpty(lastFieldValue))
			{
				errors.put(lastFieldName, "整型数据(" + lastFieldName + ")不能为空");
			} else if (!StringUtils.validateRegex(lastFieldValue, "\\d+"))
			{
				errors.put(lastFieldName, "整型数据(" + lastFieldName + ")格式不正确");
			}
		} else if (CONST.DATATYPE.Double.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			// 验证小数数据
			String lastFieldValue = request.getParameter(lastFieldName);
			if (StringUtils.isEmpty(lastFieldValue))
			{
				errors.put(lastFieldName, "小数数据(" + lastFieldName + ")不能为空");
			} else if (!StringUtils.validateRegex(lastFieldValue, "\\d+(\\.\\d+)?"))
			{
				errors.put(lastFieldName, "小数数据(" + lastFieldName + ")格式不正确");
			}
		} else if (CONST.DATATYPE.DATE.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			// 验证日期数据
			String lastFieldValue = request.getParameter(lastFieldName);
			if (StringUtils.isEmpty(lastFieldValue))
			{
				errors.put(lastFieldName, "日期数据(" + lastFieldName + ")不能为空");
			} else if (!StringUtils.validateRegex(lastFieldValue, "\\d{4}-\\d{2}-\\d{2}")
					&& !StringUtils.validateRegex(lastFieldValue, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
					&& !StringUtils.validateRegex(lastFieldValue, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
			{
				errors.put(lastFieldName, "日期数据(" + lastFieldName + ")格式不正确");
			}
		} else if (CONST.DATATYPE.String.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			String lastFieldValue = request.getParameter(lastFieldName);
			if (StringUtils.isEmpty(lastFieldValue))
			{
				errors.put(lastFieldName, "字符串数据(" + lastFieldName + ")不能为空");
			}
		} else if (CONST.DATATYPE.IntArray.getRealType().equalsIgnoreCase(lastFieldTypeName)
				|| CONST.DATATYPE.IntegerArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			String[] lastFieldValue = request.getParameterValues(lastFieldName);
			if (lastFieldValue == null)
			{
				errors.put(lastFieldName, "整形数组(" + lastFieldName + ")不能为空");
			} else
			{
				for (String value : lastFieldValue)
				{
					try
					{
						Integer.parseInt(value);
					} catch (Exception e)
					{
						errors.put(lastFieldName, "整形数组(" + lastFieldName + ")格式不正确");
						break;
					}
				}
			}
		} else if (CONST.DATATYPE.DoubleArray.getRealType().equalsIgnoreCase(lastFieldTypeName)
				|| CONST.DATATYPE.doubleArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			String[] lastFieldValue = request.getParameterValues(lastFieldName);
			if (lastFieldValue == null)
			{
				errors.put(lastFieldName, "小数数组(" + lastFieldName + ")不能为空");
			} else
			{
				for (String value : lastFieldValue)
				{
					try
					{
						Double.parseDouble(value);
					} catch (Exception e)
					{
						errors.put(lastFieldName, "小数数组(" + lastFieldName + ")格式不正确");
						break;
					}
				}
			}
		} else if (CONST.DATATYPE.StringArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
		{
			String[] lastFieldValue = request.getParameterValues(lastFieldName);
			if (lastFieldValue == null)
			{
				errors.put(lastFieldName, "字符串数组(" + lastFieldName + ")不能为空");
			}
		} else
		{
			System.out.println("****不支持的数据类型，无法完成数据校验****");
		}
	}

	/**
	 * 根据字段名称获取Servlet中的具体字段 dept.company.name，则会获取到name属性字段
	 * 
	 * @param parameters
	 * @return
	 */
	private Field getLastField(String property)
	{
		Field lastField = null;
		System.out.println("[debug] 待验证的字段全名:" + property);
		String[] propertyList = property.split("\\.");
		System.out.println("[debug] 所有节点总长度:" + propertyList.length);
		Object obj = this.servletObject;
		for (int i = 0; i < propertyList.length - 1; i++)
		{
			// getter
			try
			{
				Method getter = obj.getClass().getDeclaredMethod("get" + StringUtils.initCap(propertyList[i]));
				obj = (Object) getter.invoke(obj);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			lastField = obj.getClass().getDeclaredField(propertyList[propertyList.length - 1]);
		} catch (NoSuchFieldException | SecurityException e)
		{
			e.printStackTrace();
		}

		return lastField;
	}
}
