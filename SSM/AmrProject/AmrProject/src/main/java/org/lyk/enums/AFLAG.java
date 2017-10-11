package org.lyk.enums;

public enum AFLAG
{
	SUPER_ADMIN(1),NORMAL_EMPLOYEE(0),COMMON_ADMIN(2);
	
	private Integer value;
	private AFLAG(Integer value)
	{
		this.value = value;
	}
	
	public Integer getValue()
	{
		return value;
	}
}
