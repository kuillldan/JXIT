package actions;

import java.util.Locale;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

@SuppressWarnings("serial")

@Action(value = "EchoAction", results = {@Result(name="inputJSP",location="/pages/echo/input.jsp")})
@Namespace(value="/pages/echo")
public class EchoAction extends ActionSupport
{
	private String msg;

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	@Override
	public String execute() throws Exception
	{
		this.msg = "ECHO:" + this.msg;
		Locale locEnUS = new Locale("en", "US"); 
		System.out.println(LocalizedTextUtil.findDefaultText("info.msg", locEnUS));
		System.out.println(super.getText("info.msg"));
		
		return "inputJSP";
	}
}
