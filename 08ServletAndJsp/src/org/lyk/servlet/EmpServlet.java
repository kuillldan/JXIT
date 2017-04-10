package org.lyk.servlet;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.lyk.factory.*;
import org.lyk.utils.General;
import org.lyk.vo.*;

import com.jspsmart.upload.*;

/**
 * Servlet implementation class EmpServlet
 */
@WebServlet("/pages/back/admin/emp/EmpServlet/*")
public class EmpServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		String status = General.getStatus(request);
		String path = "/pages/common/error.jsp";
		try
		{
			if("insertPre".equals(status))
			{
				path = this.insertPre(request, response);
			}
			else if("insert".equals(status))
			{
				path = this.insert(request, response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	public String insertPre(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> map = ServiceFactory.getIEmpServiceInstance().insertPre();
		List<Emp> allEmps = (List<Emp>) map.get("allEmps");
		List<Dept> allDepts = (List<Dept>) map.get("allDepts");
		request.setAttribute("allEmps", allEmps);
		request.setAttribute("allDepts", allDepts);
		
		
		System.out.println("[debug]:" + ((List<Emp>)request.getAttribute("allEmps")).size());
		System.out.println("[debug]:" + ((List<Dept>)request.getAttribute("allDepts")).size());
		
		
		Integer deptno = 0;
		try
		{
			deptno = Integer.parseInt(request.getParameter("deptno"));
		} catch (Exception e)
		{
			deptno =  0;
		}
		
		return "/pages/back/admin/emp/emp_insert.jsp";
	}
	
	public String insert(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
		String msg = "员工信息增加成功";
		
		
		SmartUpload smart = new SmartUpload();
		smart.initialize(super.getServletConfig(), request, response);
		smart.upload();
		
		
		SmartRequest _request = smart.getRequest();
		Enumeration<String> names = _request.getParameterNames();
		
		Emp emp = new Emp();
		emp.setEmpno(Integer.parseInt(_request.getParameter("empno")));
		emp.setEname(_request.getParameter("ename"));
		emp.setJob(_request.getParameter("job"));
		
		Emp mgr = new Emp();
		mgr.setEmpno(Integer.parseInt(_request.getParameter("mgrno")));
		emp.setMgr(mgr);
		
		Dept dept = new Dept();
		dept.setDeptno(Integer.parseInt(_request.getParameter("deptno")));
		emp.setDept(dept);
		
		emp.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse(_request.getParameter("hiredate")));
		emp.setSal(Integer.parseInt(_request.getParameter("sal")));
		emp.setComm(Integer.parseInt(_request.getParameter("comm")));
		
		SmartFiles files = smart.getFiles();
		String photo = "";
		if(files.getCount() > 0 && files.getSize() > 0 && files.getFile(0).getContentType().contains("image"))
		{
			photo = UUID.randomUUID() +"."+ files.getFile(0).getFileExt();
			files.getFile(0).saveAs(this.getServletContext().getRealPath("/upload/") + photo);
		}
		else
		{
			photo = "nophoto.jpg";
		}
		
		String note = _request.getParameter("note");
		emp.setPhoto(photo);
		emp.setNote(note); 

		String url ="/pages/back/admin/emp/EmpServlet/insertPre";
		if (!ServiceFactory.getIEmpServiceInstance().insert(emp)) 
		{
			msg = "员工信息增加失败";
		} 
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/pages/common/forward.jsp";
	}
	
	
 
}
