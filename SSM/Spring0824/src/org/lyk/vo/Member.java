package org.lyk.vo;

public abstract class Member
{
	private String mid;
	private String name;
	private Integer age;
	private String flag;
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
	public String getFlag()
	{
		return flag;
	}
	public void setFlag(String flag)
	{
		this.flag = flag;
	}
	@Override
	public String toString()
	{
		return "Member [mid=" + mid + ", name=" + name + ", age=" + age + ", flag=" + flag + "]";
	} 
}
