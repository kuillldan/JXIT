package interceptor;

import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import utils.DataValidator;
import utils.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class DataValidationInterceptor extends AbstractInterceptor
{
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		ActionSupport actionSupport = (ActionSupport)actionInvocation.getAction();
		Map<String, Object> parameters = actionInvocation.getInvocationContext().getParameters();
		String requestMethod = StringUtils.getActionMethodFromRequest(request.getRequestURI());
		
		try
		{
			Field validationRuleField = actionSupport.getClass().getDeclaredField(requestMethod + "Rule");
			validationRuleField.setAccessible(true);
			String validationRuleString = (String)validationRuleField.get(actionSupport);
			boolean isValidationPassed = DataValidator.validateByRule(actionSupport, validationRuleString, parameters);
			if(isValidationPassed)
			{
				//数据校验通过
				return actionInvocation.invoke();
			}
			else
			{
				//数据校验未通过 跳转到校验失败的页面
				return requestMethod + "ValidationFailed";
			}
		}
		catch(NoSuchFieldException e)
		{
			e.printStackTrace();
			return actionInvocation.invoke();
		} 
	}

}
