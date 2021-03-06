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
import utils.CONST;
import utils.StringUtils;
import vo.Admin;
import vo.Dept;
import vo.Employee;
import vo.Jobs;
import vo.Level;

@WebServlet("/pages/front/employee/EmployeeServletFront/*")
public class EmployeeServletFront extends AbstractServlet
{
	private String insertValidation = "employee.ename|employee.sex|employee.idcard|employee.birthday|employee.school|employee.edu|employee.profession|employee.indate|employee.dept.did|employee.level.levid|employee.jobs.jid|employee.sal|employee.note";
	private String updateValidation = "employee.ename|employee.sex|employee.idcard|employee.birthday|employee.school|employee.edu|employee.profession|employee.indate|employee.dept.did|employee.level.levid|employee.jobs.jid|employee.sal|employee.note|employee.status";
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
			 
			
			List<Dept> allDepts = (List<Dept>)map.get("allDepts");
			List<Level> allLevels = (List<Level>)map.get("allLevels");
			List<Jobs> allJobs = (List<Jobs>)map.get("allJobs");
			Employee employee = (Employee)map.get("employee"); 
			request.setAttribute("allDepts", allDepts);
			request.setAttribute("allLevels", allLevels);
			request.setAttribute("allJobs", allJobs);
			request.setAttribute("employee", employee);
			System.out.println("[debug]-school:" + employee.getSchool());
		
			return EmployeePages.updateJSP;
		}
		catch(Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	public String update()
	{
		try
		{
			String newPhotoName = super.updatePhoto();
			
			if(StringUtils.isEmpty(newPhotoName))
			{
				this.employee.setPhoto(CONST.noPhoto);
			}
			else
			{
				this.employee.setPhoto(newPhotoName);
			}
			Admin admin = (Admin)super.request.getSession().getAttribute("fAdmin");
			this.employee.setAdmin(admin);
			if(ServiceFrontFactory.getIEmployeeServiceFrontInstance().update(this.employee))
			{
				return super.updateSuccessfull(EmployeePages.updatePreURL+"?employee.eid=" + this.employee.getEid());
			}
			else
			{
				return super.updateFailed(EmployeePages.updatePreURL+"?employee.eid=" + this.employee.getEid());
			} 
		}
		catch(Exception e)
		{
			return super.setSystemError(e);
		}
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
				if(null == allFileNames)
				{
					this.employee.setPhoto(CONST.noPhoto);
				}
				else
				{
					this.employee.setPhoto(allFileNames.get(0));
				}
			}
			Admin admin = (Admin)super.request.getSession().getAttribute("fAdmin");
			this.employee.setAdmin(admin);
			this.employee.setStatus(EmployeeStatus.IN.ordinal());
			  
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
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().list(currentPage, lineSize, column, keyWord);
//			map.put("allEmployees", allEmployees);
//			map.put("allEmployeesCount", allEmployeesCount);
			List<Employee> allEmployees = (List<Employee>)map.get("allEmployees");
			Integer allEmployeesCount = (Integer)map.get("allEmployeesCount");
			Integer totalPages = (allEmployeesCount + lineSize  - 1)/lineSize;
			
			request.setAttribute("allEmployees", allEmployees);
			request.setAttribute("allEmployeesCount", allEmployeesCount);
			request.setAttribute("totalPages", totalPages);
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
