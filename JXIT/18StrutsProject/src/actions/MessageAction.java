package actions;

public class MessageAction
{
	private String msg ;
	public String getMsg()
	{
		return msg;
	}
	 
	
	public String insert()
	{
		System.out.println("======================");
		System.out.println(this.msg);
		System.out.println("======================");
		return "good";
	}
}
