package action;

import javax.servlet.http.HttpServletRequest;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import vo.Member;

import com.opensymphony.xwork2.ActionSupport;

import factory.ServiceFactory;


@SuppressWarnings("serial")
@ParentPackage("root")
@Action("MemberAction")
@InterceptorRefs(value={@InterceptorRef(value="general")})
@Results(value={@Result(name="input",location="/pages/member/input.jsp")})
public class MemberAction extends ActionSupport
{
	private HttpServletRequest request = ServletActionContext.getRequest();
	private Member member = new Member();
	public Member getMember()
	{
		return member;
	}
	
	public void setMember(Member member)
	{
		this.member = member;
	}
	
	public String insert()
	{
		try
		{
			if(ServiceFactory.getIMemberServiceInstance().insert(this.member))
			{
				request.setAttribute("msg", "成员增加成功");
				request.setAttribute("url", "/pages/member/input.jsp");
				return "forwardJSP";
			}
			else
			{
				request.setAttribute("msg", "成员增加失败");
				request.setAttribute("url", "/pages/member/input.jsp");
				return "forwardJSP";
			}
		}catch(Exception e)
		{ 
			e.printStackTrace();
			return "errorJSP";
		}
	}
}
