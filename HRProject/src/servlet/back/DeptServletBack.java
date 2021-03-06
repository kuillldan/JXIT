package servlet.back;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.faces.flow.builder.ReturnBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;
import javax.xml.rpc.ServiceFactory;

import messages.DeptMessages;
import factories.ServiceBackFactory;
import pages.DeptPages;
import utils.AbstractServlet;
import utils.General;
import vo.Dept;

/**
 * Servlet implementation class DeptServletBack
 */
@SuppressWarnings("serial")
@WebServlet("/pages/back/admin/dept/DeptServletBack/*")
public class DeptServletBack extends AbstractServlet
{ 
	private String insertValidation = "dept.dname";
	private String updateValidation = "dept.dname|dept.did";
	private Dept dept = new Dept();

	public Dept getDept()
	{
		return dept;
	}

	public String insertPre()
	{
		return DeptPages.insertJSP;

	}

	public String insert()
	{
		try
		{
			if (ServiceBackFactory.getIDeptServiceBackInstance().insert(this.dept))
				return super.insertSuccessfull(DeptPages.insertPreURL);
			else
				return super.insertFailed(DeptPages.insertPreURL);

		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}

	public String updatePre()
	{
		return DeptPages.updateJSP;
	}

	public String update()
	{
		try
		{
			if (ServiceBackFactory.getIDeptServiceBackInstance().update(this.dept))
			{
				return super.updateSuccessfull(DeptPages.listURL);
			} else
			{
				return super.updateFailed(DeptPages.listURL);
			}
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}

	public String delete()
	{
		try
		{
			Set<Integer> ids = General.splitIntegers(request.getParameter("ids"));
			if(ids.size() <= 0)
				return super.deleteFailed(DeptPages.listURL);
			
			if(ServiceBackFactory.getIDeptServiceBackInstance().delete(ids))
			{
				return super.deleteSuccessfull(DeptPages.listURL);
			}
			else
			{
				return super.deleteFailed(DeptPages.listURL);
			} 
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	public String list()
	{
		try
		{
			List<Dept> allDepts = ServiceBackFactory.getIDeptServiceBackInstance().list();
			request.setAttribute("allDepts", allDepts); 
			return DeptPages.listJSP;
		}
		catch(Exception e)
		{
			return super.setSystemError(e);
		}
	}

	@Override
	protected String getUploadFolder()
	{
		return null;
	}

	@Override
	protected String getColumns()
	{
		return null;
	}

	@Override
	protected String getColumn()
	{
		return null;
	}

	@Override
	protected String getTitle()
	{
		return "部门";
	}

}
