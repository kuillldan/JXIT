package lyk.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import lyk.factory.ServiceFactory;
import lyk.vo.MemberLogin;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@ParentPackage("root")
@Action("MemberAction")
@Namespace("/pages/member")

public class MemberAction extends ActionSupport
{
	private MemberLogin memberLogin = new MemberLogin();
	 
	public MemberLogin getMemberLogin()
	{
		return memberLogin;
	}
	
	public void insert()
	{
		try
		{
			this.memberLogin.getMemberDetails().setMid(this.memberLogin.getMid());
			System.out.println(ServiceFactory.getIMemberServiceInstance().insert(this.memberLogin));
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doNothing()
	{
		System.out.println("=========================");
		System.out.println(this.memberLogin);
		System.out.println("=========================");
	}
}
