package servlet;

import javax.servlet.annotation.WebServlet;

import utils.General;
import vo.Dept;

@SuppressWarnings("serial")
@WebServlet("/dept/*")
public class DeptServlet extends DispatcherServlet
{
	//private String insertValidation = "dept.dname|dept.deptno|dept.company.name";
	
	
	public DeptServlet()
	{
		System.out.println("[debug] DeptServlet创建...." + General.getCurrentTime() );
	}
	
	private Dept dept = new Dept();
	public Dept getDept()
	{
		return dept;
	}
	
	public String insert()
	{
		try
		{
			System.out.println("dept insert --> " + this.dept);
			if(super.isUpload())
			{
				super.saveAllFiles();
			}
			return "";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	@Override
	protected String getTitle()
	{
		return "部门";
	}

	@Override
	protected String getUploadFolderName()
	{
		return "dept";
	}

	@Override
	protected String getColumns()
	{
		return "部门名称:dname|地址:d";
	}

	@Override
	protected String getColumn()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
