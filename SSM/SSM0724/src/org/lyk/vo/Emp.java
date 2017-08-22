package org.lyk.vo;
 
import java.util.Map; 

public class Emp
{
	private Integer empno;
	private String ename;
	private Double sal;
	private Map<String,String> address;
	 
	public Map<String, String> getAddress()
	{
		return address ;
	}
	public void setAddress(Map<String, String> address)
	{
		this.address = address;
	}
	public Integer getEmpno()
	{
		return empno;
	}
	public void setEmpno(Integer empno)
	{
		this.empno = empno;
	}
	public String getEname()
	{
		return ename;
	}
	public void setEname(String ename)
	{
		this.ename = ename;
	}
	public Double getSal()
	{
		return sal;
	}
	public void setSal(Double sal)
	{
		this.sal = sal;
	}
	@Override
	public String toString()
	{
		return "Emp [empno=" + empno + ", ename=" + ename + ", sal=" + sal + ", address=" + address + "]";
	} 
}
