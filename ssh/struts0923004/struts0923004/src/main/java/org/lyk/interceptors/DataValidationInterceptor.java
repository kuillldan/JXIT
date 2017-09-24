package org.lyk.interceptors;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.lyk.utils.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class DataValidationInterceptor extends AbstractInterceptor
{
	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		ActionContext actionContext = invocation.getInvocationContext();
		Object action = invocation.getAction();
		
		Method addFieldErrorMethod = action.getClass().getMethod("addFieldError", String.class,String.class);
		//getText(String key, String[] args)
		Method getTextMethod = action.getClass().getMethod("getText",String.class, String[].class);
		 
		String rule = this.getValidationRule(invocation);
		if(!StringUtils.isEmpty(rule))
		{
			String[] eachRule = rule.split("\\|");
			for(String rulePair : eachRule)
			{
				String requiredFieldName = rulePair.split(":")[0];
				String requiredFieldType = rulePair.split(":")[1];
				Map<String, Object> requestParameters = actionContext.getParameters();
				String[] actualFieldValues = (String[])requestParameters.get(requiredFieldName);
				if(actualFieldValues == null || actualFieldValues.length == 0)
				{
					String msg = (String)getTextMethod.invoke(action, "field.not.null",null);
					addFieldErrorMethod.invoke(action, requiredFieldName,msg);
					continue;
				}
				
				for(String eachValue : actualFieldValues)
				{
					if(StringUtils.isEmpty(eachValue))
					{
						String msg = (String)getTextMethod.invoke(action, "field.not.null",null);
						addFieldErrorMethod.invoke(action, requiredFieldName,msg);
						break;
					}
					
					if("Integer".equalsIgnoreCase(requiredFieldType) || "int".equalsIgnoreCase(requiredFieldType))
					{
						if(!this.validateInteger(eachValue))
						{
							addFieldErrorMethod.invoke(action, requiredFieldName,"该字段必须为整数");
							break;
						}
					}
					else if("double".equalsIgnoreCase(requiredFieldType) || "fload".equalsIgnoreCase(requiredFieldType))
					{
						if(!this.validateDouble(eachValue))
						{
							addFieldErrorMethod.invoke(action, requiredFieldName,"该字段必须为小数类型");
							break;
						}
					}
					else if("date".equalsIgnoreCase(requiredFieldType))
					{
						if(!this.validateDate(eachValue))
						{
							addFieldErrorMethod.invoke(action, requiredFieldName,"该字段必须为日期");
							break;
						}
					}
					else
					{
						System.out.println("WARN: 不支持的数据类型("+requiredFieldName+":"+requiredFieldType+"),无法完成验证." );
					}
				} 
			}
		}
//		super.getFieldErrors()
		Method getFieldErrorsMethod = action.getClass().getMethod("getFieldErrors");
		Map<String, List<String>> fieldErrors = (Map<String, List<String>>)getFieldErrorsMethod.invoke(action);
		if(fieldErrors == null )
		{
			return invocation.invoke();
		}
		
		return null;
	}
	
	private String getValidationRule(ActionInvocation invocation)
	{
		String rule = null;
		Object action = invocation.getAction();
		HttpServletRequest request = ServletActionContext.getRequest();
		String requestUri = request.getRequestURI();
		String method = requestUri.substring(requestUri.lastIndexOf("!") + 1, requestUri.lastIndexOf("."));
		String ruleName = method + "Rule";
		Field ruleField;
		try
		{
			ruleField = action.getClass().getDeclaredField(ruleName);
		} catch (NoSuchFieldException e)
		{
			e.printStackTrace();
			return null;
		} catch (SecurityException e)
		{
			e.printStackTrace();
			return null;
		}
		
		ruleField.setAccessible(true);
		try
		{
			rule = (String)ruleField.get(action);
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
			return null;
		}
		
		return rule;
	}
	private boolean validateInteger(String value )
	{
		if(value.matches("\\d+"))
		{
			return true;
		}
		{
			return false;
		}
	}
	private boolean validateDouble(String value)
	{
		if(value.matches("\\d+\\.\\d+"))
		{
			return true;
		}
		{
			return false;
		}
	}
	
	private boolean validateDate(String value)
	{
		if(value.matches("\\d{4}-\\d{2}-\\d{2}") || value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}\\d{2}") || value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}\\d{2}\\.\\d{3}"))
		{
			return true;
		}
		return true;
	}

}
