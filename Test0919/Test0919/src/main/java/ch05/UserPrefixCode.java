package ch05;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.sun.org.apache.bcel.internal.generic.NEW;

@SuppressWarnings("serial")
public class UserPrefixCode implements Serializable
{
	private String prefixCode;
	private String terminalType;
	private String userIdSuffix;

	public UserPrefixCode()
	{
	}

	public String getPrefixCode()
	{
		return prefixCode;
	}

	public void setPrefixCode(String prefixCode)
	{
		this.prefixCode = prefixCode;
	}

	public String getTerminalType()
	{
		return terminalType;
	}

	public void setTerminalType(String terminalType)
	{
		this.terminalType = terminalType;
	}

	public String getUserIdSuffix()
	{
		return userIdSuffix;
	}

	public void setUserIdSuffix(String userIdSuffix)
	{
		this.userIdSuffix = userIdSuffix;
	}

	@Override
	public int hashCode()
	{
		return 5;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;  
		UserPrefixCode other = (UserPrefixCode) obj;
		
		
		if (prefixCode == null)
		{
			if (other.prefixCode != null)
				return false;
		} else if (!prefixCode.equals(other.prefixCode))
			return false;
		
		
		if (terminalType == null)
		{
			if (other.terminalType != null)
				return false;
		} else if (!terminalType.equals(other.terminalType))
			return false;
		
		 
		
		return true;
	}

	@Override
	public String toString()
	{
		return "UserPrefixCode [prefixCode=" + prefixCode + ", terminalType=" + terminalType + ", userIdSuffix="
				+ userIdSuffix + "]";
	} 
}
