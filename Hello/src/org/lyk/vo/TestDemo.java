package org.lyk.vo;

 


public class TestDemo
{
	private Emp emp = new Emp();

	public Emp getEmp()
	{
		return emp;
	}

	public void setEmp(Emp emp)
	{
		this.emp = emp;
	}

	@Override
	public String toString()
	{
		return "TestDemo [emp=" + emp + "]";
	}
}