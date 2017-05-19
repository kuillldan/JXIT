package actions;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import utils.StringUtils;
import vo.News;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")

@ParentPackage("root")
@Action("NewsAction") 
@Results({@Result(name="input",location="/input.jsp")})
public class NewsAction extends ActionSupport
{ 
//http://localhost:8080/18StrutsProject/NewsAction!forJmeter.action
	private String msg ;
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	public void forJmeter()
	{ 
		System.out.println("===================================");
		System.out.println("[debug] msg=" + this.msg);
		System.out.println("===================================");
	}
	 
}
