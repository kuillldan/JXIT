package vo;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Adminlogs implements Serializable
{
	private Integer alid;
	private Admin admin;
	private Date logindate;
	private String ip;
	public Integer getAlid()
	{
		return alid;
	}
	public void setAlid(Integer alid)
	{
		this.alid = alid;
	}
	public Admin getAdmin()
	{
		return admin;
	}
	public void setAdmin(Admin admin)
	{
		this.admin = admin;
	}
	public Date getLogindate()
	{
		return logindate;
	}
	public void setLogindate(Date logindate)
	{
		this.logindate = logindate;
	}
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	@Override
	public String toString()
	{
		return "Adminlogs [alid=" + alid + ", admin=" + admin + ", logindate=" + logindate + ", ip=" + ip + "]";
	}
	
	
}
