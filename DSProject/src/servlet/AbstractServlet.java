package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BeanOperator;
import utils.CONST;
import utils.General;

import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;

@SuppressWarnings("serial")
public class AbstractServlet extends HttpServlet
{
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected SmartUpload smartUpload;

	public AbstractServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		this.request = request;
		this.response = response;
		
		String path = CONST.errorPage;
		String status = General.getRequestStatus(request);
		
		try
		{
			BeanOperator bo = new BeanOperator(this);
			Map<String, String> errors = new HashMap<String, String>();
			
			if(this.request.getContentType() != null && this.request.getContentType().contains("multipart/form-data"))
			{
				// 请求已封装
				this.smartUpload = new SmartUpload();
				this.smartUpload.initialize(super.getServletConfig(),this.request,this.response);
				this.smartUpload.upload();
				SmartRequest smartRequest = smartUpload.getRequest();
				bo.validateParameters(errors, status, request, smartRequest, true);
				
			}
			else
			{
				//请求未封装
				bo.validateParameters(errors, status, request, null, false);
			}
			if(errors.size() <=0 )
			{
				//验证通过 - 需要绑定数据
				
			
				
				
				System.out.println("[debug] " + errors);
				Method method = this.getClass().getMethod(status);
				path = (String)method.invoke(this);
			}
			else
			{
				//验证不通过
				request.setAttribute("errors", errors);
				path = CONST.errorPage;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			path = CONST.errorPage;
		}
		System.out.println("[debug] path: " + path );
		this.request.getRequestDispatcher(path).forward(request, response);
	}
}
