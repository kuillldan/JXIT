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

	public static void setStartUpJob()
	{
		if(scheduledExecutorServiceStart != null)
		{
			scheduledExecutorServiceStart.shutdown();
			scheduledExecutorServiceStart = null;
		}
		
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
		scheduledExecutorServiceStart = Executors.newScheduledThreadPool(10);
		scheduledExecutorServiceStart.scheduleAtFixedRate(startUpEachDayJob, delay, period, TimeUnit.MILLISECONDS);
		System.out.println( startUpEachDayJob.getJobName() + "启动成功，将在" + (delay / 1000 / 60) + "分钟后执行");
	}
	
	
	
	public static void setCloseJob()
	{
		if(scheduledExecutorServiceClose != null)
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
		scheduledExecutorServiceClose = Executors.newScheduledThreadPool(10);
		scheduledExecutorServiceClose.scheduleAtFixedRate(closeEachDayJob, delay, period, TimeUnit.MILLISECONDS);
		System.out.println( closeEachDayJob.getJobName() + "启动成功，将在" + (delay / 1000 / 60) + "分钟后执行");
	}

}
