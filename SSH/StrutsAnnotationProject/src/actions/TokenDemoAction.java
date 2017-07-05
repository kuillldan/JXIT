package actions;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@ParentPackage(value = "root")
@Action("TokenDemoAction")
@Namespace("/pages/token")
@InterceptorRefs({ @InterceptorRef("generalInterceptor"), @InterceptorRef("token") })
@Results({ @Result(name = "invalid.token", location = "/pages/common/invalidToken.jsp")})
public class TokenDemoAction extends ActionSupport
{ 

	public void insert()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Enumeration<String> parameterNames = request.getParameterNames();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
		while (parameterNames.hasMoreElements())
		{
			String parameterName = parameterNames.nextElement();
			System.out.println(parameterName + ":" + request.getParameter(parameterName));
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
	}
}