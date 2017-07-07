package bitool.job;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import bitool.enums.OpenOffMode;
import bitool.enums.OpenOffStatus;
import bitool.factory.ServiceFactory;
import bitool.servlet.InitServletGlobal;

public class StartupEachDay extends TimerTask
{
	private String jobName = null;

	public StartupEachDay(String jobName)
	{
		super();
		this.jobName = jobName;
	}

	public String getJobName()
	{
		return jobName;
	}



	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}



	@Override
	public void run()
	{
		if (OpenOffMode.AUTO.toString().equalsIgnoreCase(InitServletGlobal.currentStatus.getMode()))
		{
			try
			{
				ServiceFactory.getOpenOffManagementServiceInstance().updateStatus(
						OpenOffStatus.OPEN.toString());
				InitServletGlobal.currentStatus.setStatus(OpenOffStatus.OPEN.toString());
				System.out.println("状态更新成功:" + InitServletGlobal.currentStatus);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("JOB 未执行:" + InitServletGlobal.currentStatus);
		}
	}

	public Calendar getEarliestDate(Calendar currentDate, int hourOfDay, int minuteOfHour, int secondOfMinite)
	{
		// 计算当前时间的DAY_OF_WEEK, HOUR_OF_DAY, MINUTE,SECOND等各个字段值
		int currentDayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
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

		if (postponeToTomorrow)
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
}
