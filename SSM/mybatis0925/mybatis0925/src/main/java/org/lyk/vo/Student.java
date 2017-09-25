package org.lyk.vo;

public class Student extends Member
{
	private String school;
	private Double score;
	public String getSchool()
	{
		return school;
	}
	public void setSchool(String school)
	{
		this.school = school;
	}
	public Double getScore()
	{
		return score;
	}
	public void setScore(Double score)
	{
		this.score = score;
	}
	@Override
	public String toString()
	{
		return "Student [school=" + school + ", score=" + score + "]";
	} 
}
