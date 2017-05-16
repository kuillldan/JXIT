package utils;

import java.text.SimpleDateFormat;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class DataValidator
{
	public static boolean validateByRule(ActionSupport actionSupport, String validationRuleString,
			Map<String, Object> parameters)
	{
		boolean isValidationPassed = true;
		String[] allFieldRules = validationRuleString.split("\\|");
		for (String eachFieldRule : allFieldRules)
		{
			String[] fieldNameAndType = eachFieldRule.split(":");
			String fieldName = fieldNameAndType[0];
			String fieldType = fieldNameAndType[1];

			Object valuesInObject = parameters.get(fieldName);
			if (valuesInObject == null)
			{
				// 验证未通过
				// 客户端未传入需要的字段
				isValidationPassed = false;
				actionSupport.addFieldError(fieldName, actionSupport.getText("fieldNotProvided"));
			}

			String[] allValuesInStringArray = (String[]) valuesInObject;
			if (allValuesInStringArray.length <= 0)
			{
				isValidationPassed = false;
				actionSupport.addFieldError(fieldName, actionSupport.getText("fieldShouldNotBeEmpty"));
				continue;
			}

			for (String eachValue : allValuesInStringArray)
			{
				if (StringUtils.isEmpty(eachValue))
				{
					// 验证未通过
					// 该字段为空
					isValidationPassed = false;
					actionSupport.addFieldError(fieldName, actionSupport.getText("fieldShouldNotBeEmpty"));
					continue;
				}

				switch (fieldType)
				{
				case "String":
				{
					//String类型无需再验证
					break;
				}
				case "int":
				{
					if (!validateInteger(eachValue))
					{
						// 验证未通过
						// 非法的整型数据格式
						isValidationPassed = false;
						actionSupport.addFieldError(fieldName, actionSupport.getText("invalidIntFormat"));
					}

					break;
				}
				case "double":
				{
					if (!validateDouble(eachValue))
					{
						// 验证未通过
						// 非法的小数格式
						isValidationPassed = false;
						actionSupport.addFieldError(fieldName, actionSupport.getText("invalidDoubleFormat"));
					}

					break;
				}
				case "date":
				{
					if (!validateDate(eachValue))
					{
						// 验证未通过
						// 非法的日期格式

						isValidationPassed = false;
						actionSupport.addFieldError(fieldName, actionSupport.getText("invalidDateFormat"));
					}
					break;
				}
				default:
				{
					System.out.println("不支持的数据类型(" + fieldType + ")，本框架无法完成数据验证。");
				}
				}
			}
		}

		return isValidationPassed;
	}

	public static boolean validateInteger(String data)
	{
		try
		{
			Integer.parseInt(data);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	public static boolean validateDouble(String data)
	{
		try
		{
			Double.parseDouble(data);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	public static boolean validateDate(String data)
	{
		try
		{
			new SimpleDateFormat("yyyy-MM-dd").parse(data);
			return true;
		} catch (Exception e)
		{
			try
			{
				new SimpleDateFormat("yyyy-MM-dd HH:dd:mm").parse(data);
				return true;
			} catch (Exception e1)
			{
				try
				{
					new SimpleDateFormat("yyyy-MM-dd HH:dd:mm.SSS").parse(data);
					return true;
				} catch (Exception e2)
				{
					return false;
				}
			}
		}
	}
}
