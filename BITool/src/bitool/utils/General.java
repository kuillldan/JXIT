package bitool.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class General
{

	/**
	 * 根据HttpRequest获取对应的Servlet Action
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestStatus(HttpServletRequest request)
	{
		return request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
	}

	public static String getBasePath(HttpServletRequest request)
	{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
	}

	public static String setMsgAndUrlInRequest(HttpServletRequest request, String msg, String url)
	{
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return CONST.forwardPageJSP;
	}

	public static String setSystemError(Exception e)
	{
		e.printStackTrace();
		return CONST.errorPageJSP;
	}

	public static Calendar getEarliestDate(Calendar currentDate, int hourOfDay, int minuteOfHour, int secondOfMinite)
	{
		// 计算当前时间的DAY_OF_WEEK, HOUR_OF_DAY, MINUTE,SECOND等各个字段值
		int currentDayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);

		if (isPostponeToTomorrow(currentDate, hourOfDay, minuteOfHour, secondOfMinite))
		{
			// 设置当前日期中的DAY_OF_WEEK为当前周推迟一天
			currentDate.set(Calendar.DAY_OF_WEEK, currentDayOfWeek + 1);
		}

		// 设置当前日期中的HOUR_OF_DAY,MINUTE,SECOND为输入条件中的值。
		currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
		currentDate.set(Calendar.MINUTE, minuteOfHour);
		currentDate.set(Calendar.SECOND, secondOfMinite);
		return currentDate;
	}

	private static boolean isPostponeToTomorrow(Calendar currentDate, Integer hourOfDay, Integer minuteOfHour,
			Integer secondOfMinite)
	{
		int currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
		int currentMinute = currentDate.get(Calendar.MINUTE);
		int currentSecond = currentDate.get(Calendar.SECOND);

		// 如果输入条件中的dayOfWeek小于当前日期的dayOfWeek,则WEEK_OF_YEAR需要推迟一周
		boolean postponeToTomorrow = false;

		if (hourOfDay < currentHour)
		{
			postponeToTomorrow = true;
		} else if (hourOfDay == currentHour)
		{
			// 当输入条件与当前日期的dayOfWeek, hourOfDay相等时，
			// 如果输入条件中的minuteOfHour小于当前日期的
			// currentMinute，则WEEK_OF_YEAR需要推迟一周
			if (minuteOfHour < currentMinute)
			{
				postponeToTomorrow = true;
			} else if (minuteOfHour == currentSecond)
			{
				// 当输入条件与当前日期的dayOfWeek, hourOfDay，
				// minuteOfHour相等时，如果输入条件中的
				// secondOfMinite小于当前日期的currentSecond，
				// 则WEEK_OF_YEAR需要推迟一周
				if (secondOfMinite < currentSecond)
				{
					postponeToTomorrow = true;
				}
			}
		}

		return postponeToTomorrow;
	}

	public static void prevent(HttpServletRequest request, HttpServletResponse response, String msg, String url, Logger logger)
			throws Exception
	{
		request.setAttribute("msg", msg);
		logger.info(CONST.MESSAGE_SOURCE.getString("user.rejected", msg));
		request.getRequestDispatcher(CONST.errorPageJSP).forward(request, response);
	}

	
}
