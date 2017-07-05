package bitool.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AccountManagement implements Serializable
{
	private Integer aid;
	private String userID ;
	private String transformedUserID;
	private String userType; 
	private String ipAddress;
	public Integer getAid()
	{
		return aid;
	}
	public void setAid(Integer aid)
	{
		this.aid = aid;
	}
	public String getUserID()
	{
		return userID;
	}
	public void setUserID(String userID)
	{
		this.userID = userID;
	}
	public String getTransformedUserID()
	{
		return transformedUserID;
	}
	public void setTransformedUserID(String transformedUserID)
	{
		this.transformedUserID = transformedUserID;
	}
	public String getUserType()
	{
		return userType;
	}
	public void setUserType(String userType)
	{
		this.userType = userType;
	}
	public String getIpAddress()
	{
		return ipAddress;
	}
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	@Override
	public String toString()
	{
		return "AccountManagement [aid=" + aid + ", userID=" + userID
				+ ", transformedUserID=" + transformedUserID + ", userType="
				+ userType + ", ipAddress=" + ipAddress + "]";
	}  
}
