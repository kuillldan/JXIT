package actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@ParentPackage("root")
@Namespace("/pages/token")
@Action("PrepareAction")
@Results({@Result(name="inputJSP",location="/pages/token/input.jsp")})
public class PrepareAction extends ActionSupport
{
	public String inputPre()
	{
		return "inputJSP";
	}
}