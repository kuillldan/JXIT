package vo;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
public class Admin implements Serializable
{
	private String aid;
	private String password;
	private Integer type;
	private Date lastdate;
	private Integer flag;
	
	
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
	public Integer getType()
	{
		return type;
	}
	public void setType(Integer type)
	{
		this.type = type;
	}
	public Date getLastdate()
	{
		return lastdate;
	}
	public void setLastdate(Date lastdate)
	{
		this.lastdate = lastdate;
	}
	public Integer getFlag()
	{
		return flag;
	}
	public void setFlag(Integer flag)
	{
		this.flag = flag;
	}
	
	@Override
	public String toString()
	{
		return "Admin [aid=" + aid + ", password=" + password + ", type=" + type + ", lastdate=" + lastdate + ", flag="
				+ flag + "]";
	} 
}
