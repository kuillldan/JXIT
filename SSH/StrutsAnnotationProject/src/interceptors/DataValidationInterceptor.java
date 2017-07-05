package interceptors;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext; 

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class DataValidationInterceptor extends AbstractInterceptor
{
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{
		ActionSupport action = (ActionSupport) actionInvocation.getAction();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> parameters = actionInvocation.getInvocationContext().getParameters();
		String uri = request.getRequestURI();
		String requestMethodName = uri.substring(uri.lastIndexOf("!") + 1, uri.lastIndexOf("."));

		try
		{
			Field validationRuleField = action.getClass().getDeclaredField(requestMethodName + "Rule");
			validationRuleField.setAccessible(true);
			String validationRule = (String) validationRuleField.get(action);
			if (this.validateInternal(action, validationRule, parameters))
			{
				System.out.println("[debug]:数据验证通过");
				return actionInvocation.invoke();
			} else
			{
				System.out.println("[debug]:数据验证未通过");
				return requestMethodName + "ValidationFailed";
			}
		} catch (NoSuchFieldException e)
		{
			return actionInvocation.invoke();
		}
	}

	@SuppressWarnings("unused")
	public boolean validateInternal(ActionSupport action, String validationRule, Map<String, Object> parameters)
	{
		boolean isValidationPassed = true;
		String[] validationRuleInArray = validationRule.split("\\|");
		for (String eachValidationRule : validationRuleInArray)
		{
			String fieldName = eachValidationRule.split(":")[0];
			String fieldRealType = eachValidationRule.split(":")[1];

			Object parameterValueInObject = parameters.get(fieldName);
			if (parameterValueInObject == null)
			{
				isValidationPassed = false;
				action.addFieldError(fieldName, "未在请求中找到该字段信息");
				continue;
			}
			String[] parameterValueInStringArray = (String[]) parameterValueInObject;
			if (parameterValueInStringArray.length <= 0)
			{
				isValidationPassed = false;
				action.addFieldError(fieldName, "该字段的值不能为空");
				continue;
			}

			for (String eachParameterValue : parameterValueInStringArray)
			{
				switch (fieldRealType)
				{
					case "int":
					case "Integer" :
					{
						try
						{
							Integer.parseInt(eachParameterValue);
						}catch(NumberFormatException e)
						{
							isValidationPassed = false;
							action.addFieldError(fieldName, "整型数据格式错误");
						}
						break;
					}
					case "String":
					{
						//此处无需验证String类型，因为在Switch之前已经对String 的非空型进行了验证
						break;
					}
					case "double":
					case "Double":
					{
						try
						{
							Double.parseDouble(eachParameterValue);
						}catch(NumberFormatException e)
						{
							isValidationPassed = false;
							action.addFieldError(fieldName, "小数数据格式错误");
						}
						break;
					}
					case "Date":
					{
						try
						{ 
							Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(eachParameterValue);
						}
						catch(Exception e)
						{
							try
							{
								Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(eachParameterValue);
								
							}catch(Exception e1)
							{
								try
								{
									Date date3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(eachParameterValue);
								}
								catch(Exception e3)
								{
									isValidationPassed = false;
									action.addFieldError(fieldName, "日期格式不正确");
								}
							}
						}
						break;
					}
					default:
					{
						System.out.println("不支持的数据类型，本框架无法完成验证");
						break;
					}
				}
			}
		}
		return isValidationPassed;
	}
}