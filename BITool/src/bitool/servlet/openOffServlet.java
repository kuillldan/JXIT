package bitool.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitool.enums.OpenOffMode;
import bitool.enums.OpenOffStatus;
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
		} else if ("updateMode".equals(status))
		{
			path = this.updateMode(request, response);
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	private String updateMode(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			// 手动开闭局管理

			String mode = request.getParameter("mode");
			if (StringUtils.isEmpty(mode))
			{
				return General.setMsgAndUrlInRequest(request, CONST.invalidParams, CONST.errorPageJSP);
			}

			if (OpenOffMode.AUTO.toString().equals(mode))
			{
				ServiceFactory.getOpenOffManagementServiceInstance().updateMode(OpenOffMode.AUTO.toString());
				InitServletGlobal.currentStatus.setMode(OpenOffMode.AUTO.toString());
				
				InitServletGlobal.initStatus();
				
//
//				String startHour = String
//						.valueOf(InitServletGlobal.currentStatus.getStartTime().split(":")[0]);
//				String startMinute = String
//						.valueOf(InitServletGlobal.currentStatus.getStartTime().split(":")[1]);
//				String endHour = String.valueOf(InitServletGlobal.currentStatus.getEndTime().split(":")[0]);
//				String endMinute = String.valueOf(InitServletGlobal.currentStatus.getEndTime().split(":")[1]);
//
//				request.setAttribute("startHour", startHour);
//				request.setAttribute("startMinute", startMinute);
//				request.setAttribute("endHour", endHour);
//				request.setAttribute("endMinute", endMinute);
//
//				this.updateTime(request, response);
//				System.out.println("****自动状态已开启.");
			} else if (OpenOffMode.MANUAL.toString().equals(mode))
			{
				//设置模式(mode)为手动MANUAL
				ServiceFactory.getOpenOffManagementServiceInstance().updateMode(OpenOffMode.MANUAL.toString());
				InitServletGlobal.currentStatus.setMode(OpenOffMode.MANUAL.toString());
				
				//设置状态status为CLOSED
				ServiceFactory.getOpenOffManagementServiceInstance().updateStatus(OpenOffStatus.CLOSED.toString());
				InitServletGlobal.currentStatus.setStatus(OpenOffStatus.CLOSED.toString());
				
				
			} else if (OpenOffMode.MAINTAINANCE.toString().equals(mode))
			{
				//设置模式(mode)为手动MAINTAINANCE
				ServiceFactory.getOpenOffManagementServiceInstance().updateMode(OpenOffMode.MAINTAINANCE.toString());
				InitServletGlobal.currentStatus.setMode(OpenOffMode.MAINTAINANCE.toString());
				
				//设置状态status为ADMIN_OPEN
				ServiceFactory.getOpenOffManagementServiceInstance().updateStatus(OpenOffStatus.ADMIN_OPEN.toString());
				InitServletGlobal.currentStatus.setStatus(OpenOffStatus.ADMIN_OPEN.toString());
			} 
			else
			{
				return General.setMsgAndUrlInRequest(request, CONST.invalidParams, CONST.errorPageJSP);
			}

			return General.setMsgAndUrlInRequest(request, CONST.modeUpdateSuccess, CONST.updateResultJSP);
		} catch (Exception e)
		{
			return General.setSystemError(e);
		}
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

				if (OpenOffMode.AUTO.toString().equals(InitServletGlobal.currentStatus.getMode()))
				{
					// 如果当前模式为自动模式
					// 需要根据当前时间更改状态(CLOSE/OPEN) 数据库和内存中的状态都要改

					if ("00:00".equals(vo.getStartTime()) && "00:00".equals(vo.getEndTime()))
					{
						// 设置全天持续运行

						// 1 关闭所有Scheduler
						InitServletGlobal.shutdownExecutorServiceStart();
						InitServletGlobal.shutdownExecutorServiceClose();

						// 2 设置数据库状态为OPEN
						ServiceFactory.getOpenOffManagementServiceInstance().updateStatus(
								OpenOffStatus.OPEN.toString());

						// 3 设置内存为OPEN
						InitServletGlobal.currentStatus.setStatus(OpenOffStatus.OPEN.toString());

						System.out.println("*****当前状态已经设置为全天AUTO运行");
					} else
					{
						// 如果不是全天运行 根据当前时间判断当前状态是否为OPEN/CLOSE
						// 在数据库和内存中都要更新
						Calendar currentTime = Calendar.getInstance();
						Long currentTimeInLong = currentTime.getTimeInMillis();

						Calendar startTime = Calendar.getInstance();
						startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
						startTime.set(Calendar.MINUTE, Integer.parseInt(startMinute));
						startTime.set(Calendar.SECOND, 0);
						startTime.set(Calendar.MILLISECOND, 0);
						Long startTimeInLong = startTime.getTimeInMillis();

						Calendar endTime = Calendar.getInstance();
						endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
						startTime.set(Calendar.MINUTE, Integer.parseInt(endMinute));
						startTime.set(Calendar.SECOND, 0);
						startTime.set(Calendar.MILLISECOND, 0);
						Long endTimeInLong = endTime.getTimeInMillis();

						if (startTimeInLong <= currentTimeInLong
								&& currentTimeInLong <= endTimeInLong
								&& OpenOffStatus.CLOSED.toString().equals(
										InitServletGlobal.currentStatus.getStatus()))
						{
							// 当前时间在设置时间范围内
							// 且当前状态为CLOSED
							// -->
							// 需要将状态更新为OPEN

							// 1 设置数据库状态为OPEN
							ServiceFactory.getOpenOffManagementServiceInstance().updateStatus(
									OpenOffStatus.OPEN.toString());

							// 2 设置内存为OPEN
							InitServletGlobal.currentStatus.setStatus(OpenOffStatus.OPEN.toString());
							System.out.println("当前时间在设置时间范围内，调整为AUTO OPEN");
						} else if ((currentTimeInLong < startTimeInLong || currentTimeInLong > endTimeInLong)
								&& OpenOffStatus.OPEN.toString().equals(
										InitServletGlobal.currentStatus.getStatus()))
						{
							ServiceFactory.getOpenOffManagementServiceInstance().updateStatus(
									OpenOffStatus.CLOSED.toString());
							InitServletGlobal.currentStatus.setStatus(OpenOffStatus.CLOSED.toString());
							System.out.println("当前时间不在设置时间范围内，调整为AUTO CLOSED");
						}

						InitServletGlobal.setStartUpJob();
						InitServletGlobal.setCloseJob();
						System.out.println("后台Job已经设定..");
					}
				}

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
