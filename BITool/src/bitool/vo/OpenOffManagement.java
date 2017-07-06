package bitool.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OpenOffManagement implements Serializable
{
	private Integer oid;
	private String status;
	private String mode;
	private String startTime;
	private String endTime;
	public Integer getOid()
	{
		return oid;
	}
	public void setOid(Integer oid)
	{
		this.oid = oid;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
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
		return "OpenOffManagement [oid=" + oid + ", status=" + status + ", mode=" + mode + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	} 
}
