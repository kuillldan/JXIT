//package bitool.job;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.TimerTask;
//
//import bitool.enums.OpenOffMode;
//import bitool.enums.OpenOffStatus;
//import bitool.factory.ServiceFactory;
//import bitool.servlet.InitServletGlobal;
//import bitool.vo.OpenOffManagement;
//
//public class CloseEachDay extends TimerTask
//{
//	private String jobName = null;
//
//	public CloseEachDay(String jobName)
//	{
//		super();
//		this.jobName = jobName;
//	}
//
//	public String getJobName()
//	{
//		return jobName;
//	}
//
//
//
//	public void setJobName(String jobName)
//	{
//		this.jobName = jobName;
//	}
//
//
//
//	@Override
//	public void run()
//	{
//		OpenOffManagement currentOpenOffManagementStatus = InitServletGlobal
//				.loadCurrentOpenOffManagementStatus();
//		if (OpenOffMode.AUTO.toString().equalsIgnoreCase(currentOpenOffManagementStatus.getMode()))
//		{
//			try
//			{
//				ServiceFactory.getOpenOffManagementServiceInstance().updateStatus(
//						OpenOffStatus.CLOSED.toString());
//				
//				
//				System.out.println("状态更新成功:" + currentOpenOffManagementStatus);
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//		else
//		{
//			System.out.println("JOB 未执行:" + currentOpenOffManagementStatus);
//		}
//	}
//}
