package org.lyk.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor
{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		Map<String, Object> sessionAttributes = invocation.getInvocationContext().getSession();
		if (null == (String) sessionAttributes.get("mid") || "".equals((String) sessionAttributes.get("mid")))
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("msg", "你还没有登录");
			request.setAttribute("url", "login.jsp");
			return "forward.page";
		} else
		{
			return invocation.invoke();
		}
	}

}
