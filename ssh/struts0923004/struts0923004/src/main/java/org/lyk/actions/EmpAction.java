package org.lyk.actions;

import org.apache.struts2.ServletActionContext;
import org.lyk.vo.Emp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class EmpAction extends ActionSupport
{
	private String showRule = "empno:int|ename:String";
	private Emp emp = new Emp();

	
	public EmpAction()
	{
//		super.getText(aTextName)
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
		System.out.println("**********list执行:" + this.emp);
		return "emp.list";
	}

	public String show()
	{
		System.out.println("*****show" + this.emp);
		return "emp.show";
	}

	@Override
	public void validate()
	{
		String ename = this.getEmp().getEname();
		if(ename == null  || "".equals(ename))
		{
			System.out.println("验证错误:ename不能为空!");
			super.addFieldError("ename", "ename不能为空!");
		}
		System.out.println("**********validation执行:" + this.emp);
	}
}
