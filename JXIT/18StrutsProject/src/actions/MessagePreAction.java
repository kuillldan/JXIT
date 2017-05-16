package actions;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MessagePreAction extends ActionSupport
{
	public String insertPre()
	{
		System.out.println("========insertPre");
		return "message_input.page";
	}
}
