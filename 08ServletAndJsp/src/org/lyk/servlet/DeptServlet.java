package org.lyk.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lyk.factory.ServiceFactory;
import org.lyk.vo.Dept;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/pages/back/admin/dept/DeptServlet/*")
public class DeptServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeptServlet()
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
		request.setCharacterEncoding("UTF-8");
		String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
		String path = "/pages/common/error.jsp";
		if ("insert".equals(status))
		{
			path = this.insert(request, response);
		} else if ("delete".equals(status))
		{
			path = this.delete(request, response);
		} else if ("list".equals(status))
		{
			path = this.list(request, response);
		} else if ("listDetails".equals(status))
		{
			path = this.listDetails(request, response);
		} else if ("updatePre".equals(status))
		{
			path = this.updatePre(request, response);
		} else if ("update".equals(status))
		{
			path = this.update(request);
		}

		System.out.println("========================================");
		System.out.println("[status]:" + status);
		System.out.println("[msg]:" + request.getAttribute("msg"));
		System.out.println("[url]:" + request.getAttribute("url"));
		System.out.println("[path]:" + path);
		System.out.println("========================================");

		request.getRequestDispatcher(path).forward(request, response);
	}

	private String update(HttpServletRequest request)
	{
		String url = "/pages/back/admin/dept/DeptServlet/list";
		String msg = "部门信息更新成功";

		Dept vo = new Dept();
		vo.setDeptno(Integer.parseInt(request.getParameter("deptno")));
		vo.setDname(request.getParameter("dname"));
		vo.setLoc(request.getParameter("loc"));

		try
		{
			if (!ServiceFactory.getIDeptServiceInstance().update(vo)) 
			{
				msg = "部门信息更新失败";
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return "/pages/common/error.jsp";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "/pages/common/forward.jsp";
	}

	private String updatePre(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			Integer deptno = Integer.parseInt(request.getParameter("deptno"));
			Dept dept = ServiceFactory.getIDeptServiceInstance().updatePre(deptno);
			request.setAttribute("dept", dept);

		} catch (Exception e)
		{
			return "/pages/common/error.jsp";
		}

		return "/pages/back/admin/dept/dept_update.jsp";
	}

	private String listDetails(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List<Dept> allDepts = ServiceFactory.getIDeptServiceInstance().listAllDetails();
			request.setAttribute("allDepts", allDepts);
		} catch (Exception e)
		{
			return "/pages/common/error.jsp";
		}

		return "/pages/back/admin/dept/dept_list_details.jsp";
	}

	private String list(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List<Dept> allDepts = ServiceFactory.getIDeptServiceInstance().list();
			request.setAttribute("allDepts", allDepts);
		} catch (Exception e)
		{
			return "/pages/common/error.jsp";
		}

		return "/pages/back/admin/dept/dept_list.jsp";
	}

	private String delete(HttpServletRequest request, HttpServletResponse response)
	{
		String msg = "";
		String url = "/pages/back/admin/dept/DeptServlet/list";

		String _ids = request.getParameter("ids");
		String[] deptnos = _ids.split("\\|");
		Set<Integer> ids = new HashSet<Integer>();
		for (String id : deptnos)
		{
			ids.add(Integer.parseInt(id));
		}

		try
		{
			if (ServiceFactory.getIDeptServiceInstance().delete(ids))
			{
				msg = "部门信息删除成功";

			} else
			{
				msg = "部门信息删除失败";
			}
		} catch (Exception e)
		{
			return "/pages/common/error.jsp";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/pages/common/forward.jsp";
	}

	private String insert(HttpServletRequest request, HttpServletResponse response)
	{
		String msg = "部门信息增加成功";
		String url = "/pages/back/admin/dept/dept_insert.jsp";

		Dept vo = new Dept();
		vo.setDeptno(Integer.parseInt(request.getParameter("deptno")));
		vo.setDname(request.getParameter("dname"));
		vo.setLoc(request.getParameter("loc"));

		try
		{
			if (!ServiceFactory.getIDeptServiceInstance().insert(vo))
			{
				msg = "部门信息增加失败";
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return "/pages/common/error.jsp";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "/pages/common/forward.jsp";
	}

}
