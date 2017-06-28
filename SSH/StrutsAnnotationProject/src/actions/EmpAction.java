package actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import vo.Emp;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Action(value = "EmpAction", results = { @Result(name = "showJSP", location = "/pages/emp/show.jsp"),
		@Result(name = "goodJSP", location = "/pages/common/good.jsp"),
		@Result(name="input",location="/pages/emp/insert.jsp")})

public class EmpAction extends ActionSupport
{
	private Emp emp = new Emp();
	public Emp getEmp()
	{
		return emp;
	}

	public void setEmp(Emp emp)
	{
		this.emp = emp;
	}
 
	public String insert()
	{
		System.out.println(this.emp);
		return "showJSP";
	}
}
