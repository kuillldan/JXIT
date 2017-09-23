package org.lyk.actions;

import org.lyk.vo.Emp;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class EmpAction extends ActionSupport
{
	private Emp emp = new Emp();
	
	public EmpAction()
	{
		System.out.println("创建EmpAction -- " + Thread.currentThread().getName());
	}
	

	public Emp getEmp()
	{
		return emp;
	}

	public void setEmp(Emp emp)
	{
		this.emp = emp;
	}


	public String list()
	{
		System.out.println("*****list");
		return "emp.list";
	}
	public String show()
	{
		System.out.println("*****show");
		return "emp.show";
	}
}
