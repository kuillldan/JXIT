//package bitool.servlet;
//
//import java.io.IOException;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//
//import bitool.enums.OpenOffMode;
//import bitool.enums.OpenOffStatus;
//import bitool.factory.ServiceFactory;
//import bitool.job.CloseEachDay;
//import bitool.job.StartupEachDay;
//import bitool.utils.General;
//import bitool.vo.OpenOffManagement;
//
///**
// * 该Servlet在启动容器自动运行 1 将开闭局管理对象加载到内存中 2 启动自动设置开闭局状态的Job
// */
//@WebServlet(value = "/RunJobs", loadOnStartup = 1)
//public class InitServletGlobal extends HttpServlet
//{
//	private static final long serialVersionUID = 1L;
//	public static final Integer MAX_THREAD_NUMBER = 10;
//	private static final Long PERIOD = 1000L * 60L * 60L * 24L;
//	private static final Logger logger = Logger.getLogger(InitServletGlobal.class);
//
//	// public static OpenOffManagement currentOpenOffManagementStatus;
//	public static ScheduledExecutorService scheduledExecutorServiceStart = null;
//	public static ScheduledExecutorService scheduledExecutorServiceClose = null;
//
//	@Override
//	public void init() throws ServletException
//	{
//		// TODO Auto-generated method stub
//
//		super.init();
//	}
//
//	public InitServletGlobal()
//	{
//		super();
//		try
//		{
//			// 根据开闭局状态设置status
//			// initStatus();
//			// 启动AUTO开局Job
//			// setStartUpJob();
//			// 启动AUTO闭局Job
//			// setCloseJob();
//		} catch (Exception e)
//		{
//			logger.error(e.getMessage(), e);
//		}
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//	{
//		this.doPost(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//	{
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * 关闭设置AUTO开局的Job
//	 */
//	// public static void shutdownExecutorServiceStart()
//	// {
//	// if (scheduledExecutorServiceStart != null)
//	// {
//	// scheduledExecutorServiceStart.shutdown();
//	// scheduledExecutorServiceStart = null;
//	// }
//	// }
//	//
//	// /**
//	// * 关闭设置AUTO闭局的Job
//	// */
//	// public static void shutdownExecutorServiceClose()
//	// {
//	// if (scheduledExecutorServiceClose != null)
//	// {
//	// scheduledExecutorServiceClose.shutdown();
//	// scheduledExecutorServiceClose = null;
//	// }
//	// }
//	//
//	// public static Long getDelay(OpenOffManagement
//	// currentOpenOffManagementStatus)
//	// {
//	// Calendar currentTime = Calendar.getInstance();
//	// long currentTimeInLong = currentTime.getTimeInMillis();
//	// Integer startHour =
//	// Integer.parseInt(currentOpenOffManagementStatus.getStartTime().split(":")[0]);
//	// Integer startMinute =
//	// Integer.parseInt(currentOpenOffManagementStatus.getStartTime().split(":")[1]);
//	//
//	// Calendar nextRuningTime = General.getEarliestDate(currentTime, startHour,
//	// startMinute, 0);
//	// long nextRuningTimeInLong = nextRuningTime.getTimeInMillis();
//	//
//	// Long delay = nextRuningTimeInLong - currentTimeInLong;
//	// return delay;
//	// }
//	//
//	// /**
//	// * 根据内存中的OpenOffManagement对象，获取开局时间，然后设置Job的运行参数并启动Job
//	// */
//	// public static void setStartUpJob()
//	// {
//	// shutdownExecutorServiceStart();
//	//
//	// OpenOffManagement currentOpenOffManagementStatus =
//	// loadCurrentOpenOffManagementStatus();
//	// StartupEachDay startUpEachDayJob = new
//	// StartupEachDay("StartUpEachDayJob");
//	// Long delay = getDelay(currentOpenOffManagementStatus);
//	// scheduledExecutorServiceStart =
//	// Executors.newScheduledThreadPool(MAX_THREAD_NUMBER);
//	// scheduledExecutorServiceStart.scheduleAtFixedRate(startUpEachDayJob,
//	// delay, PERIOD, TimeUnit.MILLISECONDS);
//	// logger.info(startUpEachDayJob.getJobName() +
//	// " successfully started, will be running after " + (delay / 1000 / 60)
//	// + " minutes");
//	// }
//	//
//	// /**
//	// * 根据内存中的OpenOffManagement对象，获取闭局时间，然后设置Job的运行参数并启动Job
//	// */
//	// public static void setCloseJob()
//	// {
//	// if (scheduledExecutorServiceClose != null)
//	// {
//	// scheduledExecutorServiceClose.shutdown();
//	// scheduledExecutorServiceClose = null;
//	// }
//	//
//	// OpenOffManagement currentOpenOffManagementStatus =
//	// loadCurrentOpenOffManagementStatus();
//	// CloseEachDay closeEachDayJob = new CloseEachDay("CloseEachDayJob");
//	// Long delay = getDelay(currentOpenOffManagementStatus);
//	// scheduledExecutorServiceClose =
//	// Executors.newScheduledThreadPool(MAX_THREAD_NUMBER);
//	// scheduledExecutorServiceClose.scheduleAtFixedRate(closeEachDayJob, delay,
//	// PERIOD, TimeUnit.MILLISECONDS);
//	// logger.info(closeEachDayJob.getJobName() +
//	// " successfully started, will be running after " + (delay / 1000 / 60)
//	// + " minutes");
//	// }
//	//
//	// public static OpenOffManagement loadCurrentOpenOffManagementStatus()
//	// {
//	// try
//	// {
//	// return
//	// ServiceFactory.getOpenOffManagementServiceInstance().findOpenOffManagement();
//	// } catch (Exception e)
//	// {
//	// logger.error(e.getMessage(), e);
//	// return null;
//	// }
//	// }
//	//
//	// /**
//	// * 如果模式mode为自动开闭局管理，根据当前时间设置当前状态status
//	// */
//	// public static void initStatus()
//	// {
//	// // 如果当前状态为自动开闭局管理，则需要判断当前时间是否在开局时间范围内
//	// // 如果当前时间在开局范围内，则将状态设置为OPEN，否则设置为CLOSED
//	//
//	// OpenOffManagement currentOpenOffManagementStatus =
//	// loadCurrentOpenOffManagementStatus();
//	//
//	// if
//	// (OpenOffMode.AUTO.toString().equals(currentOpenOffManagementStatus.getMode()))
//	// {
//	//
//	// String startHour =
//	// currentOpenOffManagementStatus.getStartTime().split(":")[0];
//	// String startMinute =
//	// currentOpenOffManagementStatus.getStartTime().split(":")[1];
//	// String endHour =
//	// currentOpenOffManagementStatus.getEndTime().split(":")[0];
//	// String endMinute =
//	// currentOpenOffManagementStatus.getEndTime().split(":")[1];
//	//
//	// Map<String, Long> times = new HashMap<String, Long>();
//	// getTimes(startHour, startMinute, endHour, endMinute, times);
//	//
//	// Long startTimeInLong = times.get("startTimeInLong");
//	// Long currentTimeInLong = times.get("currentTimeInLong");
//	// Long endTimeInLong = times.get("endTimeInLong");
//	//
//	// // 如果当前时间在开局时间范围内，且当前为自动开闭局管理，则将状态更新为OPEN，否则更新为CLOSED
//	// if (startTimeInLong <= currentTimeInLong && currentTimeInLong <=
//	// endTimeInLong)
//	// {
//	// currentOpenOffManagementStatus.setStatus(OpenOffStatus.OPEN.toString());
//	// logger.info("Current status has been updated into '" +
//	// OpenOffStatus.OPEN.toString() + "'");
//	// } else
//	// {
//	// currentOpenOffManagementStatus.setStatus(OpenOffStatus.CLOSED.toString());
//	// logger.info("[initialize] Current status has been updated into '" +
//	// OpenOffStatus.CLOSED.toString() + "'");
//	// }
//	//
//	// try
//	// {
//	// ServiceFactory.getOpenOffManagementServiceInstance().updateStatus(currentOpenOffManagementStatus.getStatus());
//	// } catch (Exception e)
//	// {
//	// logger.error(e.getMessage(), e);
//	// }
//	// }
//	// }
//	//
//	// public static void getTimes(String startHour, String startMinute, String
//	// endHour, String endMinute, Map<String, Long> times)
//	// {
//	// Calendar currentTime = Calendar.getInstance();
//	// Long currentTimeInLong = currentTime.getTimeInMillis();
//	//
//	// Calendar startTime = Calendar.getInstance();
//	// startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
//	// startTime.set(Calendar.MINUTE, Integer.parseInt(startMinute));
//	// startTime.set(Calendar.SECOND, 0);
//	// startTime.set(Calendar.MILLISECOND, 0);
//	// Long startTimeInLong = startTime.getTimeInMillis();
//	//
//	// Calendar endTime = Calendar.getInstance();
//	// endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
//	// endTime.set(Calendar.MINUTE, Integer.parseInt(endMinute));
//	// endTime.set(Calendar.SECOND, 0);
//	// endTime.set(Calendar.MILLISECOND, 0);
//	// Long endTimeInLong = endTime.getTimeInMillis();
//	//
//	// times.put("currentTimeInLong", currentTimeInLong);
//	// times.put("startTimeInLong", startTimeInLong);
//	// times.put("endTimeInLong", endTimeInLong);
//	// }
//}
