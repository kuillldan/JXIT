package bitool.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import bitool.enums.OpenOffMode;
import bitool.enums.OpenOffStatus;
import bitool.exception.RecordUpdatedException;
import bitool.factory.ServiceFactory;
import bitool.utils.CONST;
import bitool.utils.General;
import bitool.utils.StringUtils;
import bitool.vo.OpenOffManagement;

/**
 * Servlet implementation class openOffServlet
 */
// @WebServlet("/pages/openOff/openOffServlet/*")
@WebServlet("/svc_state_manage/*")
public class OpenOffServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(OpenOffServlet.class);

	// private static final String IPADDRESS = "ipAddress";

	private static final String INVALID_PARAMS = "invalidParams";
	private static final String UNSUPPORTED_SERVLET_ACTION = "unsupported.servlet.action";
	private static final String MODE_UPDATE_SUCCESS = "modeUpdateSuccess";
	private static final String TIME_UPDATE_SUCCESS = "timeUpdateSuccess";
	private static final String TIME_UPDATE_FAILED = "timeUpdateFailed";
	private static final String SET_MANAGEMENT_MODE_TO = "set.openOffManagement.to";
	private static final String RECORD_ALLREADY_UPDATED = "record.allready.updated.by.another.program";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OpenOffServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String status = General.getRequestStatus(request);
		// servlet 执行完后的跳转路径
		// 默认为error页面
		String path = CONST.errorPageJSP;
		if ("show".equalsIgnoreCase(status))
		{
			// 显示开闭局管理页面
			path = this.show(request, response);
		} else if ("updateTime".equalsIgnoreCase(status))
		{
			// 更新开闭局时间
			path = this.updateTime(request, response);
		} else if ("updateMode".equals(status))
		{
			// 更新开闭局管理模式
			path = this.updateMode(request, response);
		} else
		{
			// 未知的servlet请求，跳转到error页面
			request.setAttribute("msg", CONST.MESSAGE_SOURCE.getString(UNSUPPORTED_SERVLET_ACTION, status));
			logger.error(CONST.MESSAGE_SOURCE.getString(UNSUPPORTED_SERVLET_ACTION, status));
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * 更新开闭局管理的模式 AUTO/MANUAL/MAINTANANCE
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String updateMode(HttpServletRequest request, HttpServletResponse response)
	{
		String ipAddress = (String) request.getAttribute(CONST.IPADDRESS);
		String mode = request.getParameter("mode");
		String modtime = request.getParameter("modtime");
		
		try
		{
			// 手动开闭局管理 
			// 判断开闭局管理的传入参数'mode'是否为空
			if (StringUtils.isEmpty(mode) || StringUtils.isEmpty(modtime))
			{
				logger.warn(CONST.MESSAGE_SOURCE.getString(INVALID_PARAMS, "(mode or modtime is null)"));
				return General.setMsgAndUrlInRequest(request,
						CONST.MESSAGE_SOURCE.getString(INVALID_PARAMS, "(mode or modtime is null)"), CONST.errorPageJSP);
			}

			if (OpenOffMode.AUTO.toString().equals(mode))
			{// 设置mode为自动开闭局管理
				ServiceFactory.getOpenOffManagementServiceInstance().updateMode(OpenOffMode.AUTO.toString(),
						Long.parseLong(modtime));
				logger.info(CONST.MESSAGE_SOURCE.getString(SET_MANAGEMENT_MODE_TO, ipAddress, OpenOffMode.AUTO.toString()));
			} else if (OpenOffMode.MANUAL.toString().equals(mode))
			{
				// 设置模式(mode)为手动MANUAL
				ServiceFactory.getOpenOffManagementServiceInstance().updateMode(OpenOffMode.MANUAL.toString(),
						Long.parseLong(modtime));
				logger.info(CONST.MESSAGE_SOURCE.getString(SET_MANAGEMENT_MODE_TO, ipAddress, OpenOffMode.MANUAL.toString()));

			} else if (OpenOffMode.MAINTAINANCE.toString().equals(mode))
			{
				// 设置模式(mode)为手动MAINTAINANCE
				ServiceFactory.getOpenOffManagementServiceInstance().updateMode(OpenOffMode.MAINTAINANCE.toString(),
						Long.parseLong(modtime));
				logger.info(CONST.MESSAGE_SOURCE.getString(SET_MANAGEMENT_MODE_TO, ipAddress, OpenOffMode.MAINTAINANCE.toString()));

			} else
			{
				// 当前仅支持AUTO/MANUAL/MAINTAINANCE三种模式
				logger.error(CONST.MESSAGE_SOURCE.getString(INVALID_PARAMS, "( current mode'" + mode + "' is not supported)"));
				return General.setMsgAndUrlInRequest(request,
						CONST.MESSAGE_SOURCE.getString(INVALID_PARAMS, "( current mode'" + mode + "' is not supported)"),
						CONST.errorPageJSP);
			}

			request.setAttribute("msg", CONST.MESSAGE_SOURCE.getString(MODE_UPDATE_SUCCESS));
			return CONST.updateResultJSP;
		} catch (RecordUpdatedException e)
		{
			logger.error(ipAddress + ":" + CONST.MESSAGE_SOURCE.getString(RECORD_ALLREADY_UPDATED));
			request.setAttribute("msg", CONST.MESSAGE_SOURCE.getString(RECORD_ALLREADY_UPDATED));
			return CONST.errorPageJSP;
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return General.setSystemError(e);
		}
	}

	/**
	 * 更新AUTO开闭局管理的启动/结束时间
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String updateTime(HttpServletRequest request, HttpServletResponse response)
	{
		String modtime = request.getParameter("modtime");
		String startHour = request.getParameter("startHour");
		String startMinute = request.getParameter("startMinute");
		String endHour = request.getParameter("endHour");
		String endMinute = request.getParameter("endMinute");

		String ipAddress = (String) request.getAttribute(CONST.IPADDRESS);

		// 判断传入的开闭局时间是否为空
		// 判断传入的最近一次修改时间是否为空
		// 如果为空则跳转到错误页面
		if (StringUtils.isEmpty(startHour) || StringUtils.isEmpty(startMinute) || StringUtils.isEmpty(endHour)
				|| StringUtils.isEmpty(endMinute) || StringUtils.isEmpty(modtime))
		{
			logger.error(ipAddress
					+ ":"
					+ CONST.MESSAGE_SOURCE.getString(INVALID_PARAMS,
							" StartTime and EndTime or modtime should not be blank(empty)"));
			return General.setMsgAndUrlInRequest(request, CONST.MESSAGE_SOURCE.getString(INVALID_PARAMS,
					" StartTime and EndTime or modtime should not be blank(empty)"), CONST.errorPageJSP);
		}

		OpenOffManagement vo = new OpenOffManagement();
		vo.setStartTime(startHour + ":" + startMinute);
		vo.setEndTime(endHour + ":" + endMinute);

		try
		{
			if (startHour.equals(endHour) && startMinute.equals(endMinute))
			{
				// 开闭局时间不能相同
				logger.error(ipAddress + ":"
						+ CONST.MESSAGE_SOURCE.getString("startTimeAndEndTimeShouldNotBeTheSame", startHour + ":" + startMinute));
				request.setAttribute("msg",
						CONST.MESSAGE_SOURCE.getString("startTimeAndEndTimeShouldNotBeTheSame", startHour + ":" + startMinute));
				return CONST.updateResultJSP;
			}

			if (ServiceFactory.getOpenOffManagementServiceInstance().updateTime(vo, Long.parseLong(modtime)))
			{
				logger.info(ipAddress + ":OpenOffManagement StartTime has been set to " + vo.getStartTime());
				logger.info(ipAddress + ":OpenOffManagement EndTime has been set to " + vo.getEndTime());
				logger.info(ipAddress + ":" + CONST.MESSAGE_SOURCE.getString(TIME_UPDATE_SUCCESS));
				request.setAttribute("msg", CONST.MESSAGE_SOURCE.getString(TIME_UPDATE_SUCCESS));

				return CONST.updateResultJSP;
			} else
			{
				logger.error(ipAddress + ":" + CONST.MESSAGE_SOURCE.getString(TIME_UPDATE_FAILED));
				request.setAttribute("msg", CONST.MESSAGE_SOURCE.getString(TIME_UPDATE_FAILED));
				return CONST.updateResultJSP;
			}
		} catch (RecordUpdatedException re)
		{
			logger.error(ipAddress + ":" + CONST.MESSAGE_SOURCE.getString(RECORD_ALLREADY_UPDATED));
			request.setAttribute("msg", CONST.MESSAGE_SOURCE.getString(RECORD_ALLREADY_UPDATED));
			return CONST.errorPageJSP;
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return General.setSystemError(e);
		}
	}

	/**
	 * 显示开闭局管理状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String show(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			OpenOffManagement openOffManagement = ServiceFactory.getOpenOffManagementServiceInstance().findOpenOffManagement();
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
			request.setAttribute("modtime", openOffManagement.getModtime().getTime());

			return CONST.openOffShowJSP;
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return CONST.errorPageJSP;
		}
	}
}
