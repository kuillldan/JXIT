package org.lyk.vo;

import javax.persistence.*;

@Entity
@Table(name="t_member")
public class Member
{
	@Id
	@Column(name="sys_id")
	private String sysId;
	
	private Integer mid;
	private String name;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	private Integer age;
	private String address;
	
	private String comment;

	@Override
	public String toString()
	{
		return "Member [sysId=" + sysId + ", mid=" + mid + ", name=" + name + ", phoneNumber=" + phoneNumber + ", age="
				+ age + ", address=" + address + ", comment=" + comment + "]";
	}

	public String getSysId()
	{
		return sysId;
	}

	public void setSysId(String sysId)
	{
		this.sysId = sysId;
	}

	public Integer getMid()
	{
		return mid;
	}

	public void setMid(Integer mid)
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

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	} 
}
