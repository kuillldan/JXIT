package bitool.vo;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class OpenOffManagement implements Serializable
{
	private Integer oid; 
	private String mode;
	private String startTime;
	private String endTime;
	private Timestamp modtime;
	
	public Timestamp getModtime()
	{
		return modtime;
	}
	public void setModtime(Timestamp modtime)
	{
		this.modtime = modtime;
	}
	public Integer getOid()
	{
		return oid;
	}
	public void setOid(Integer oid)
	{
		this.oid = oid;
	} 
	public String getMode()
	{
		return mode;
	}
	public void setMode(String mode)
	{
		this.mode = mode;
	}
	public String getStartTime()
	{
		return startTime;
	}
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}
	public String getEndTime()
	{
		return endTime;
	}
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	@Override
	public String toString()
	{
		return "OpenOffManagement [oid=" + oid + ", mode=" + mode + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", modtime=" + modtime + "]";
	} 
}
