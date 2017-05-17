package actions;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

@SuppressWarnings("serial")
public class ConvertAction extends ActionSupport
{
	private Locale loc;
	public Locale getLoc()
	{
		return loc;
	}
	public void setLoc(Locale loc)
	{
		this.loc = loc;
	}
	
	public String showInfo()
	{ 
		String welcomeMsg = LocalizedTextUtil.findDefaultText("welcomeMsg", this.loc); 
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("welcomeMsg", welcomeMsg);
		return "welcomeJSP";
	}
}
