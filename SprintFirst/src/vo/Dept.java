package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Dept implements Serializable
{
	private Integer deptno;
	private String dname; 
	private Boolean isGoodMan;
	 
	public Dept()
	{
		// TODO Auto-generated constructor stub
		System.out.println("Dept 创建");
	}
	 
 	public Boolean getIsGoodMan()
	{
		return isGoodMan;
	}
 	
 	public void setIsGoodMan(Boolean isGoodMan)
	{
		this.isGoodMan = isGoodMan;
	}



	public Integer getDeptno()
	{
		return deptno;
	}
	public void setDeptno(Integer deptno)
	{
		System.out.println("***** Dept.deptno setter");
		this.deptno = deptno;
	}
	public String getDname()
	{
		return dname;
	}
	public void setDname(String dname)
	{
		System.out.println("***** Dept.dname setter");
		this.dname = dname;
	}

	@Override
	public String toString()
	{
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", isGoodMan=" + isGoodMan + "]";
	}


	
}
