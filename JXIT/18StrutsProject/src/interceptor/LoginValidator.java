package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import utils.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class LoginValidator extends AbstractInterceptor
{

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		if(StringUtils.isEmpty((String)session.getAttribute("mid")))
		{
			request.setAttribute("msg", "你还没有登录，请先登录!");
			request.setAttribute("url", "/login.jsp");
			return "forward.page";
		}
		else
		{
			return actionInvocation.invoke();
		} 
	}
}
