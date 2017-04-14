package org.lyk.vo;
 

public class Student implements InfoAccessible
{
	private String schoolName;

	public String getSchoolName()
	{
		return schoolName;
	}

	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}

	@Override
	public void showInfo()
	{
		System.out.println(this.toString());
	}

	@Override
	public String toString()
	{
		return "Student [schoolName=" + schoolName + "]";
	} 
}
