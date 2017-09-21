package ch05;

public class Student 
{
	private String name;
	private String major;
	
	
	public Student(String name, String major)
	{
		this.name = name;
		this.major = major;
	}
	
	public String getMajor()
	{
		return major;
	}
 
	public String getDescription()
	{
		return "a student majoring in " + this.major;
	} 
}