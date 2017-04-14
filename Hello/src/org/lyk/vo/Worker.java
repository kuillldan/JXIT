package org.lyk.vo;

public class Worker implements InfoAccessible
{
	private String compnayName;
	 
	
	public String getCompnayName()
	{
		return compnayName;
	}


	public void setCompnayName(String compnayName)
	{
		this.compnayName = compnayName;
	}


	@Override
	public String toString()
	{
		return "Worker [compnayName=" + compnayName + "]";
	}


	@Override
	public void showInfo()
	{
		// TODO Auto-generated method stub
		System.out.println(this.toString());
	}

}
