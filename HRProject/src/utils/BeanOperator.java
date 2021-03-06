package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
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

	/**
	 * 绑定数据
	 * 
	 * @param request
	 * @param smartRequest
	 * @param isEncryped
	 */
	public void setValueAutomatic(HttpServletRequest request, SmartRequest smartRequest, boolean isEncryped)
	{ 

		Enumeration<String> allParameterNames = null;
		
		if(!isEncryped)
		{
			allParameterNames = request.getParameterNames();
		}
		else
		{
			allParameterNames = smartRequest.getParameterNames();
		}
		while (allParameterNames.hasMoreElements())
		{
			String eachParameterName = allParameterNames.nextElement();
			if (!eachParameterName.contains("."))
			{
				continue;
			}
			Map<String, Object> map = this.getLastField(eachParameterName);
			Field lastField = (Field) map.get("lastField");
			Object realObject = (Object) map.get("realObject");

			lastField.setAccessible(true);
			String lastFieldTypeName = lastField.getType().getSimpleName();

			if (!isEncryped)
			{
				try
				{
					this.bindData(lastFieldTypeName, request, smartRequest, eachParameterName, lastField, realObject,
							false);
				} catch (IllegalArgumentException | IllegalAccessException | ParseException e)
				{
					e.printStackTrace();
				}
			} else
			{
				// 数据已经封装
				try
				{
					this.bindData(lastFieldTypeName, request, smartRequest, eachParameterName, lastField, realObject,
							true);
				} catch (IllegalArgumentException | IllegalAccessException | ParseException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	private void bindData(String lastFieldTypeName, HttpServletRequest request, SmartRequest smartRequest,
			String eachParameterName, Field lastField, Object realObject, boolean isEncryped)
			throws IllegalArgumentException, IllegalAccessException, ParseException
	{ 
		// 绑定普通字段(非数组)
		if (!lastFieldTypeName.contains("[]"))
		{
			String eachParameterValue = null;
			if (!isEncryped)
			{
				eachParameterValue = request.getParameter(eachParameterName);
			} else
			{
				eachParameterValue = smartRequest.getParameter(eachParameterName);
			}

			
			if (!StringUtils.isEmpty(eachParameterValue))
			{
				if (CONST.DATATYPE.Int.getRealType().equalsIgnoreCase(lastFieldTypeName)
						|| CONST.DATATYPE.Integer.getRealType().equalsIgnoreCase(lastFieldTypeName))
				{
					if (StringUtils.validateRegex(eachParameterValue, "\\d+"))
					{
						// 整型数据验证成功
						lastField.set(realObject, Integer.parseInt(eachParameterValue));
					} else
					{
						// 整型数据验证失败
						lastField.set(realObject, null);
					}
				} else if (CONST.DATATYPE.Double.getRealType().equalsIgnoreCase(lastFieldTypeName))
				{
					if (StringUtils.validateRegex(eachParameterValue, "\\d+(\\.\\d+)?"))
					{
						// Double数据验证成功
						lastField.set(realObject, Double.parseDouble(eachParameterValue));
					} else
					{
						// Double数据验证失败
						lastField.set(realObject, null);
					}
				} else if (CONST.DATATYPE.DATE.getRealType().equalsIgnoreCase(lastFieldTypeName))
				{
 
					
					if (StringUtils.validateRegex(eachParameterValue, "\\d{4}-\\d{2}-\\d{2}"))
					{// Date数据验证成功
						lastField.set(realObject, new SimpleDateFormat("yyyy-MM-dd").parse(eachParameterValue));
					} else if (StringUtils.validateRegex(eachParameterValue,
							"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))
					{// Date数据验证成功
						lastField
								.set(realObject, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(eachParameterValue));
					} else if(StringUtils.validateRegex(eachParameterValue,
							"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{1}"))
					{ 
						lastField.set(realObject,
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(eachParameterValue));
					}
					else if (StringUtils.validateRegex(eachParameterValue,
							"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3}"))
					{// Date数据验证成功
						lastField.set(realObject,
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(eachParameterValue));
					} else
					{// Date数据验证失败
						lastField.set(realObject, null);
					}

				} else if (CONST.DATATYPE.String.getRealType().equalsIgnoreCase(lastFieldTypeName))
				{
					// String数据 直接赋值
					lastField.set(realObject, eachParameterValue);
				} else
				{
					System.out.println("******不支持的数据类型，无法完成自动赋值******");
				}
			} else
			{
				// 传入的数据为空 赋null值
				lastField.set(realObject, null);
			}

		} else
		{
			// 数组
			String[] eachParameterValues = null;
			if (!isEncryped)
			{
				eachParameterValues = request.getParameterValues(eachParameterName);
			} else
			{
				eachParameterValues = smartRequest.getParameterValues(eachParameterName);
			}

			if (eachParameterValues != null)
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
						lastField.set(realObject, new Object[] { acturalValues });
					}
					else
					{
						lastField.set(realObject, new Object[] { null });
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
						lastField.set(realObject, acturalValues);

					}
					else
					{
						lastField.set(realObject, new Object[] { null });
					}
				} else if (CONST.DATATYPE.DoubleArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
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
						lastField.set(realObject, acturalValues);
					}
					else
					{
						lastField.set(realObject, new Object[] { null });
					}
				} else if (CONST.DATATYPE.doubleArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
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
						lastField.set(realObject, acturalValues);
					}
					else
					{
						lastField.set(realObject, new Object[] { null });
					}
				} else if (CONST.DATATYPE.StringArray.getRealType().equalsIgnoreCase(lastFieldTypeName))
				{
					String[] acturalValues = new String[eachParameterValues.length];
					for (int i = 0; i < eachParameterValues.length; i++)
					{
						acturalValues[i] = eachParameterValues[i];
					}
					lastField.set(realObject, acturalValues);
				} else
				{
					System.out.println("******不支持的数据类型，无法完成数组的自动赋值******");
				}
			}
			else
			{
				//传入数组为空 赋null值
				lastField.set(realObject, new Object[] { null });
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
				Field lastField = (Field) this.getLastField(property).get("lastField");
				String lastFieldTypeName = lastField.getType().getSimpleName();

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
					&& !StringUtils.validateRegex(lastFieldValue, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{1}")
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
					&& !StringUtils.validateRegex(lastFieldValue, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{1}")
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
	private Map<String, Object> getLastField(String property)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Field lastField = null;
		String[] propertyList = property.split("\\.");
		Object realObject = this.servletObject;
		for (int i = 0; i < propertyList.length - 1; i++)
		{
			// getter
			try
			{
				Method getter = realObject.getClass().getDeclaredMethod("get" + StringUtils.initCap(propertyList[i]));
				realObject = (Object) getter.invoke(realObject);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
		try
		{ 
			lastField = realObject.getClass().getDeclaredField(propertyList[propertyList.length - 1]);
		} catch (NoSuchFieldException | SecurityException e)
		{
			e.printStackTrace();
		}
		map.put("lastField", lastField);
		map.put("realObject", realObject);
		return map;
	}
}
