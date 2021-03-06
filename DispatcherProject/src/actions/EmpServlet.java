package actions;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Employee;

@SuppressWarnings("serial")
@WebServlet("/pages/back/admin/emp/EmpServlet/*")
public class EmpServlet extends DispatcherServlet
{
	private String insertValidation = "emp.ename|emp.empno|emp.dept.company.foundDate|emp.dept.company.subCompany"; 
	private Employee emp = new Employee();
	public Employee getEmp()
	{
		return emp;
	}
	
	public String list(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("*******list*******");
		return "emp.list.page";
	}
	
	public String insert(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			System.out.println("*******insert*******" + this.emp);
			List<String> allFileNames = super.saveFiles(request, "image");
			System.out.println("****" + allFileNames +"*****");
			return super.setPathAndMessage(request, response, "emp.insert.page", "emp.insert.success");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error.page";
		}
	}
	
	

	@Override
	protected String getUploadFolder()
	{
		return "emp";
	}

	@Override
	protected String getObjectName()
	{
		return "雇员";
	}
}
