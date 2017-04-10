package vo;

import java.util.Date;

public class Admin
{
	private String aid;
	private String password;
	private Date lastdate;
	public String getAid()
	{
		return aid;
	}
	public void setAid(String aid)
	{
		this.aid = aid;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public Date getLastdate()
	{
		return lastdate;
	}
	public void setLastdate(Date lastdate)
	{
		this.lastdate = lastdate;
	}
	@Override
	public String toString()
	{
		return "Admin [aid=" + aid + ", password=" + password + ", lastdate=" + lastdate + "]";
	}
	
	
}
