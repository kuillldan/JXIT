package bitool.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitool.factory.ServiceFactory;
import bitool.utils.CONST;
import bitool.utils.General;
import bitool.utils.StringUtils;
import bitool.vo.OpenOffManagement;

/**
 * Servlet implementation class openOffServlet
 */
@WebServlet("/pages/openOff/openOffServlet/*")
public class openOffServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public openOffServlet()
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
		String path = CONST.errorPageJSP;
		if ("show".equalsIgnoreCase(status))
		{
			path = this.show(request, response);
		} else if ("updateTime".equalsIgnoreCase(status))
		{
			path = this.updateTime(request, response);
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	private String updateTime(HttpServletRequest request, HttpServletResponse response)
	{
		String startHour = request.getParameter("startHour");
		String startMinute = request.getParameter("startMinute");
		String endHour = request.getParameter("endHour");
		String endMinute = request.getParameter("endMinute");

		if (StringUtils.isEmpty(startHour) || StringUtils.isEmpty(startMinute)
				|| StringUtils.isEmpty(endHour) || StringUtils.isEmpty(endMinute))
		{
			return General.setMsgAndUrlInRequest(request, CONST.invalidParams, CONST.errorPageJSP);
		}

		OpenOffManagement vo = new OpenOffManagement();
		vo.setStartTime(startHour + ":" + startMinute);
		vo.setEndTime(endHour + ":" + endMinute);

		try
		{
			if (ServiceFactory.getOpenOffManagementServiceInstance().updateTime(vo))
			{
				InitServletGlobal.currentStatus.setStartTime(vo.getStartTime());
				InitServletGlobal.currentStatus.setEndTime(vo.getEndTime());
				InitServletGlobal.setStartUpJob();
				InitServletGlobal.setCloseJob();
				
				request.setAttribute("msg", CONST.timeUpdateSuccessMsg);
				
				return CONST.updateResultJSP;
			} else
			{
				request.setAttribute("msg", CONST.timeUpdateFailedMsg);
				return CONST.updateResultJSP;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return General.setSystemError(e);
		}
	}


	private String show(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			OpenOffManagement openOffManagement = ServiceFactory.getOpenOffManagementServiceInstance()
					.findOpenOffManagement();
			request.setAttribute("status", openOffManagement.getStatus());
			request.setAttribute("mode", openOffManagement.getMode());
			String startTime = openOffManagement.getStartTime();
			String endTime = openOffManagement.getEndTime();

			String startHour = startTime.split(":")[0];
			String startMinute = startTime.split(":")[1];
			String endHour = endTime.split(":")[0];
			String endMinute = endTime.split(":")[1];

			request.setAttribute("startTime", startTime);
			request.setAttribute("endTime", endTime);
			request.setAttribute("startHour", startHour);
			request.setAttribute("startMinute", startMinute);
			request.setAttribute("endHour", endHour);
			request.setAttribute("endMinute", endMinute);

			return "/pages/openOff/show.jsp";
		} catch (Exception e)
		{
			e.printStackTrace();
			return CONST.errorPageJSP;
		}

	}

}
