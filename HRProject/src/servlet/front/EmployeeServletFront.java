package servlet.front;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import pages.EmployeePages;
import enums.EmployeeStatus;
import factories.ServiceBackFactory;
import factories.ServiceFrontFactory;
import utils.AbstractServlet;
import vo.Admin;
import vo.Dept;
import vo.Employee;
import vo.Jobs;
import vo.Level;

@WebServlet("/pages/front/employee/EmployeeServletFront/*")
public class EmployeeServletFront extends AbstractServlet
{
	private String insertValidation = "employee.ename|employee.sex|employee.idcard|employee.birthday|employee.school|employee.edu|employee.profession|employee.indate|employee.dept.did|employee.level.levid|employee.jobs.jid|employee.sal|employee.note";
	private String updateValidation = "employee.ename|employee.sex|employee.idcard|employee.birthday|employee.school|employee.edu|employee.profession|employee.indate|employee.dept.did|employee.level.levid|employee.jobs.jid|employee.sal|employee.note";
	private String updatePreValidation = "employee.eid";
	Employee employee = new Employee();
	public Employee getEmployee()
	{
		return employee;
	}
	
	
	public String updatePre()
	{
		try
		{
			Map<String, Object> map  = ServiceFrontFactory.getIEmployeeServiceFrontInstance().updatePre(this.employee.getEid());
			
//			map.put("allDepts", allDepts);
//			map.put("allLevels", allLevels);
//			map.put("allJobs", allJobs);
			
			List<Dept> allDepts = (List<Dept>)map.get("allDepts");
			List<Level> allLevels = (List<Level>)map.get("allLevels");
			List<Jobs> allJobs = (List<Jobs>)map.get("allJobs");
			Employee employee = (Employee)map.get("employee");
			System.out.println("[debug]-email:" + employee.getEmail());
			request.setAttribute("allDepts", allDepts);
			request.setAttribute("allLevels", allLevels);
			request.setAttribute("allJobs", allJobs);
			request.setAttribute("employee", employee);
			
		
			return EmployeePages.updateJSP;
		}
		catch(Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	public String update()
	{
		return "";
	}
	
	public String insertPre()
	{
		try
		{
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().insertPre();
			List<Dept> allDepts = (List<Dept>)map.get("allDepts");
			//获取所有的职位信息
			List<Jobs> allJobs = (List<Jobs>)map.get("allJobs");
			List<Level> allLevels = (List<Level>)map.get("allLevels");
			
			request.setAttribute("allDepts", allDepts);
			request.setAttribute("allJobs", allJobs);
			request.setAttribute("allLevels", allLevels);
			return EmployeePages.insertJSP;
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	public String insert()
	{
		try
		{
			if(super.isUpload())
			{
				List<String> allFileNames = super.saveFiles("image");
				this.employee.setPhoto(allFileNames.get(0));
			}
			Admin admin = (Admin)super.request.getSession().getAttribute("fAdmin");
			this.employee.setAdmin(admin);
			this.employee.setStatus(EmployeeStatus.IN.ordinal());
			
			Dept dept = new Dept();
			
			
			if(ServiceFrontFactory.getIEmployeeServiceFrontInstance().insert(this.employee))
				return super.insertSuccessfull(EmployeePages.insertPreURL);
			else
				return super.insertFailed(EmployeePages.insertPreURL);
		}
		catch(Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	public String list()
	{
		try
		{	
			super.handleSplit();
			System.out.println("[debug]- column:" + column);
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().list(currentPage, lineSize, column, keyWord);
//			map.put("allEmployees", allEmployees);
//			map.put("allEmployeesCount", allEmployeesCount);
			List<Employee> allEmployees = (List<Employee>)map.get("allEmployees");
			Integer allEmployeesCount = (Integer)map.get("allEmployeesCount");
			Integer totalPages = (allEmployeesCount + lineSize  - 1)/lineSize;
			
			request.setAttribute("allEmployees", allEmployees);
			request.setAttribute("allEmployeesCount", allEmployeesCount);
			request.setAttribute("totalPages", totalPages);
			System.out.println("[debug]-currentPage:" + currentPage + ",lineSize:" + lineSize + ",totalPages:" + totalPages);
			return EmployeePages.listAllJSP;
		}
		catch(Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	@Override
	protected String getUploadFolder()
	{
		return "employee";
	}

	@Override
	protected String getTitle()
	{
		return "员工";
	}

	@Override
	protected String getColumns()
	{
		return "员工姓名:ename|毕业学校:school|专业:profession|身份证号:idcard|职位:job|学历:edu";
	}

	@Override
	protected String getColumn()
	{
		return "ename";
	}

}
