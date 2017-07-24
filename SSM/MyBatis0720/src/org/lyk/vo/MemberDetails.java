package org.lyk.vo;

public class MemberDetails
{
	private String mid;
	private String name;
	private Integer age;
	private MemberLogin login;
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
	public Integer getAge()
	{
		return age;
	}
	public void setAge(Integer age)
	{
		this.age = age;
	}
	public MemberLogin getLogin()
	{
		return login;
	}
	public void setLogin(MemberLogin login)
	{
		this.login = login;
	}
	
	@Override
	public String toString()
	{
		return "MemberDetails [mid=" + mid + ", name=" + name + ", age=" + age + ", login=" + login + "]";
	}
}
