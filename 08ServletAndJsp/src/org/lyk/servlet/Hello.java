package org.lyk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lyk.factory.ServiceFactory;
import org.lyk.vo.Dept;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello.asp")
public class Hello extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public void destroy()
	{
		System.out.println("========Hello Servlet 销毁========");
		try
		{
			Thread.sleep(9000);
		}
		catch(Exception e)
		{}
	}

	@Override
	public void init() throws ServletException
	{
		System.out.println("========Hello Servlet 初始化========");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Hello()
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
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		String path = "/welcom.jsp";
		try
		{
			List<Dept> allDepts = ServiceFactory.getIDeptServiceInstance().list();
			
			
			System.out.println("[allDepts Count]" + allDepts.size());
			
			
			
			Map<String, Dept> map = new HashMap<String, Dept>();
			
			int i = 0; 
			for(Dept dept : allDepts)
			{
				System.out.println("[dname: ]" + dept.getDname());
				map.put("dept" + i, dept);
				i++;
			}
			
			request.setAttribute("msg", "Hello, This is Mars.");
			request.setAttribute("allDepts", allDepts);
			request.setAttribute("map", map);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			path = "/pages/common/error.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
