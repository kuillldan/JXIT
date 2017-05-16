package interceptor;

import java.util.Enumeration;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


@SuppressWarnings("serial")
public class MyInterceptor extends AbstractInterceptor
{

	@Override
	public void init()
	{
		// TODO Auto-generated method stub
		super.init();
		System.out.println("++++初始化MyInterceptor++++");
	}
	
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("++++销毁MyInterceptor++++");
	}
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{ 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		
		
		System.out.println("++++执行拦截器++++");
		System.out.println("拦截器:" + session.getId());
		
		Enumeration<String> parameterNames = request.getParameterNames();
		System.out.println("=================================");
		while(parameterNames.hasMoreElements())
		{
			String parameterName = parameterNames.nextElement();
			System.out.println(parameterName + ":" + request.getParameter(parameterName) );
		}
		System.out.println("=================================");
		return invocation.invoke();
	}

}
