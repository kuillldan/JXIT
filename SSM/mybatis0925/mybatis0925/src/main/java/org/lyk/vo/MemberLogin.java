package org.lyk.vo;

import java.io.Serializable;

public class MemberLogin implements Serializable
{
	private String mid;
	private String password;
	private MemberDetails memberDetails;
	
	
	public MemberDetails getMemberDetails()
	{
		return memberDetails;
	}

	public void setMemberDetails(MemberDetails memberDetails)
	{
		this.memberDetails = memberDetails;
	}

	public MemberLogin()
	{
		// TODO Auto-generated constructor stub
	}

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public String toString()
	{
		return "MemberLogin [mid=" + mid + ", password=" + password + ", memberDetails=" + memberDetails + "]";
	}
}
