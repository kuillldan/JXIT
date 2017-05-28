package lyk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.HibernateSessionFactory;


@SuppressWarnings("serial")
@WebServlet("/pages/sessionDemo/*")
public class HibernateSessionDemo extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("HttpSessionID 1:" + request.getSession().getId() );
		System.out.println("HttpSessionID 2:" + request.getSession().getId() );
		System.out.println("HibernateSession hasCode 1:" + HibernateSessionFactory.getSession().hashCode());
		System.out.println("HibernateSession hasCode 2:" + HibernateSessionFactory.getSession().hashCode());
	}
	
}
