package bitool.test;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.params.ClientPNames;

import bitool.enums.UserType;
import dbc.DatabaseConnection;

 
public class Hello
{
	public static void main(String[] args)
	{
		String dd = ClientPNames.HANDLE_REDIRECTS;
		System.out.println(dd);
	}
	
	public static void schedule()
	{
		StartupEachDay startupEachDay = new StartupEachDay("打开引擎");
		Calendar currentTime = Calendar.getInstance();
		long currentTimeInLong = currentTime.getTime().getTime();
		Calendar nextRuningTime = startupEachDay.getEarliestDate(currentTime, 13, 52, 0);
		long nextRuningTimeInLong = nextRuningTime.getTime().getTime();
		
		System.out.println(currentTimeInLong);
		System.out.println(nextRuningTimeInLong);
		Long delay = nextRuningTimeInLong - currentTimeInLong;
		//long period = 24 * 60 *60 * 1000;
		long period = 60 * 1000;
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
		System.out.println("delay=" + delay);
		scheduledExecutorService.scheduleAtFixedRate(startupEachDay, delay, period, TimeUnit.MILLISECONDS);
	}
}