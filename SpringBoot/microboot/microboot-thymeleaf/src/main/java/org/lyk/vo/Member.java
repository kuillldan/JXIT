package org.lyk.vo;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -391111992367759026L;
	private String mid;
	private String name;
	private String gender;
	private Integer age;
	private String phoneNumber;
	private Date hireDate;
	
	public Member()
	{
		// TODO Auto-generated constructor stub
	}
	
	

	public Integer getAge()
	{
		return age;
	}



	public void setAge(Integer age)
	{
		this.age = age;
	}



	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}



	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate()
	{
		return hireDate;
	}

	public void setHireDate(Date hireDate)
	{
		this.hireDate = hireDate;
	}

	@Override
	public String toString()
	{
		return "Member [mid=" + mid + ", name=" + name + ", gender=" + gender + ", phoneNumber=" + phoneNumber
				+ ", hireDate=" + hireDate + "]";
	} 
}
