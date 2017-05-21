package interceptors;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import utils.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class LoginValidationInterceptor extends AbstractInterceptor
{
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(StringUtils.isEmpty((String)session.getAttribute("mid")))
		{
			return "loginJSP";
		}
		else
		{
			return actionInvocation.invoke();
		}
	}

}