package org.lyk.enums;

public enum SFLAG
{
	ADMIN(1),NORMAL(0);
	
	private Integer value;
	private SFLAG(Integer value)
	{
		this.value = value;
	}
	
	public Integer getValue()
	{
		return value;
	}
}
