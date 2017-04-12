package servlet.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceFactory;

import com.jspsmart.upload.SmartFiles;
import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;

import factories.ServiceFrontFactory;
import utils.CONST;
import utils.General;
import vo.Member;

/**
 * Servlet implementation class MemberInfoServletFront
 */
@WebServlet("/pages/front/member/MemberInfoServletFront/*")
public class MemberInfoServletFront extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberInfoServletFront()
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
		String path = CONST.pageError;
		String status = General.getStatus(request);
		
		if("updatePre".equals(status))
		{
			path = this.updatePre(request);
		}else if("update".equals(status))
		{
			path = this.update(request,response);
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
	
	private String updatePre(HttpServletRequest request)
	{
		try
		{
			String mid = (String)(request.getSession().getAttribute("mid"));
			Member member = ServiceFrontFactory.getIMemberServiceFrontInstance().updatePre(mid);
			request.setAttribute("member", member);
			
			return CONST.pageMemberFrontShowJSP;
		}
		catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}

	private String update(HttpServletRequest _request,HttpServletResponse _response)
	{
		try
		{
			SmartUpload smart = new SmartUpload();
			smart.initialize(super.getServletConfig(), _request, _response);
			smart.upload();
			SmartRequest request = smart.getRequest();
			String mid = (String)(_request.getSession().getAttribute("mid"));
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String oldPhoto = request.getParameter("oldPhoto");
			String photo = CONST.noPhoto;
			
			Member member = new Member();
			member.setMid(mid);
			member.setName(name);
			member.setAddress(address);
			member.setPhone(phone); 
			
			photo = General.updatePhoto(smart, oldPhoto, photo, _request, "/photos/member/");
			member.setPhoto(photo);
			 
			String msg = null;
			String url = null;
			String referer = _request.getHeader("referer");
			if(ServiceFrontFactory.getIMemberServiceFrontInstance().update(member))
			{
				msg = "用户信息更新成功";
			}
			else
			{
				msg = "用户信息更新失败";
			}
			url = "/pages/front/member/MemberInfoServletFront/" + referer.substring(referer.lastIndexOf("/") + 1);
			return General.setMsgAndUrlInRequest(_request, msg, url);
		}catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}
}
