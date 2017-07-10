package bitool.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitool.enums.OpenOffMode;
import bitool.enums.OpenOffStatus;
import bitool.factory.ServiceFactory;
import bitool.job.CloseEachDay;
import bitool.job.StartupEachDay;
import bitool.vo.OpenOffManagement;

/**
 * Servlet implementation class RunJobs
 */
@WebServlet(value = "/RunJobs", loadOnStartup = 1)
public class InitServletGlobal extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public static final Integer MAX_THREAD_NUMBER = 10;

	public static OpenOffManagement currentStatus;
	public static ScheduledExecutorService scheduledExecutorServiceStart = null;
	public static ScheduledExecutorService scheduledExecutorServiceClose = null;

	@Override
	public void init() throws ServletException
	{
		// TODO Auto-generated method stub
		super.init();
	}

	public InitServletGlobal()
	{
		super();
		try
		{
			currentStatus = ServiceFactory.getOpenOffManagementServiceInstance().findOpenOffManagement();
			this.initStatus();
			InitServletGlobal.setStartUpJob();
			InitServletGlobal.setCloseJob();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		// TODO Auto-generated method stub
	}

	public static void shutdownExecutorServiceStart()
	{
		if (scheduledExecutorServiceStart != null)
		{
			scheduledExecutorServiceStart.shutdown();
			scheduledExecutorServiceStart = null;
		}
	}

	public static void shutdownExecutorServiceClose()
	{
		if (scheduledExecutorServiceClose != null)
		{
			scheduledExecutorServiceClose.shutdown();
			scheduledExecutorServiceClose = null;
		}
	}

	public static void setStartUpJob()
	{
		shutdownExecutorServiceStart();

		StartupEachDay startUpEachDayJob = new StartupEachDay("StartUpEachDayJob");
		Calendar currentTime = Calendar.getInstance();
		long currentTimeInLong = currentTime.getTime().getTime();
		Integer startHour = Integer.parseInt(currentStatus.getStartTime().split(":")[0]);
		Integer startMinute = Integer.parseInt(currentStatus.getStartTime().split(":")[1]);

		Calendar nextRuningTime = startUpEachDayJob.getEarliestDate(currentTime, startHour, startMinute, 0);
		long nextRuningTimeInLong = nextRuningTime.getTime().getTime();

		Long delay = nextRuningTimeInLong - currentTimeInLong;
		//
		long period = 60 * 1000;
		scheduledExecutorServiceStart = Executors.newScheduledThreadPool(MAX_THREAD_NUMBER);
		scheduledExecutorServiceStart.scheduleAtFixedRate(startUpEachDayJob, delay, period,
				TimeUnit.MILLISECONDS);
		System.out.println(startUpEachDayJob.getJobName() + "启动成功，将在" + (delay / 1000 / 60) + "分钟后执行");
	}

	public static void setCloseJob()
	{
		if (scheduledExecutorServiceClose != null)
		{
			scheduledExecutorServiceClose.shutdown();
			scheduledExecutorServiceClose = null;
		}

		CloseEachDay closeEachDayJob = new CloseEachDay("CloseEachDayJob");
		Calendar currentTime = Calendar.getInstance();
		long currentTimeInLong = currentTime.getTime().getTime();
		Integer endHour = Integer.parseInt(currentStatus.getEndTime().split(":")[0]);
		Integer endMinute = Integer.parseInt(currentStatus.getEndTime().split(":")[1]);

		Calendar nextRuningTime = closeEachDayJob.getEarliestDate(currentTime, endHour, endMinute, 0);
		long nextRuningTimeInLong = nextRuningTime.getTime().getTime();

		Long delay = nextRuningTimeInLong - currentTimeInLong;
		//
		long period = 60 * 1000;
		scheduledExecutorServiceClose = Executors.newScheduledThreadPool(MAX_THREAD_NUMBER);
		scheduledExecutorServiceClose.scheduleAtFixedRate(closeEachDayJob, delay, period,
				TimeUnit.MILLISECONDS);
		System.out.println(closeEachDayJob.getJobName() + "启动成功，将在" + (delay / 1000 / 60) + "分钟后执行");
	}

	/**
	 * 如果模式mode为自动开闭局管理，根据当前时间设置当前状态status
	 */
	public static void initStatus()
	{
		if (OpenOffMode.AUTO.toString().equals(currentStatus.getMode()))
		{

			if ("00:00".equals(currentStatus.getStartTime()) && "00:00".equals(currentStatus.getEndTime()))
			{
				// 设置全天持续运行

				// 1 关闭所有Scheduler
				InitServletGlobal.shutdownExecutorServiceStart();
				InitServletGlobal.shutdownExecutorServiceClose();

				// 2 设置数据库状态为OPEN
				try
				{
					ServiceFactory.getOpenOffManagementServiceInstance().updateStatus(
							OpenOffStatus.OPEN.toString());
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 3 设置内存为OPEN
				InitServletGlobal.currentStatus.setStatus(OpenOffStatus.OPEN.toString());

				System.out.println("*****当前状态已经设置为全天AUTO运行");
			} else
			{
				String startHour = currentStatus.getStartTime().split(":")[0];
				String startMinute = currentStatus.getStartTime().split(":")[1];
				String endHour = currentStatus.getEndTime().split(":")[0];
				String endMinute = currentStatus.getEndTime().split(":")[1];

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

				if (startTimeInLong <= currentTimeInLong && currentTimeInLong <= endTimeInLong)
				{
					currentStatus.setStatus(OpenOffStatus.OPEN.toString());
				} else
				{
					currentStatus.setStatus(OpenOffStatus.CLOSED.toString());
				}

				System.out.println("当前状态:" + currentStatus.getStatus());

				try
				{
					ServiceFactory.getOpenOffManagementServiceInstance().updateStatus(
							currentStatus.getStatus());
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
