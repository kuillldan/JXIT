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
		schedule();
	}
	
	public static void schedule()
	{
		StartupEachDay startupEachDay = new StartupEachDay("打开引擎");
		Calendar currentTime = Calendar.getInstance();
		long currentTimeInLong = currentTime.getTime().getTime();
		Calendar nextRuningTime = startupEachDay.getEarliestDate(currentTime, 15, 42, 0);
		long nextRuningTimeInLong = nextRuningTime.getTime().getTime();
		
		
		Long delay = nextRuningTimeInLong - currentTimeInLong;
		//long period = 24 * 60 *60 * 1000;
		long period = 60 * 1000;
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
		System.out.println("delay=" + delay);
		scheduledExecutorService.scheduleAtFixedRate(startupEachDay, delay, period, TimeUnit.MILLISECONDS);
		
		System.out.println("===============");
		currentTime = Calendar.getInstance();
		currentTimeInLong = currentTime.getTime().getTime();
		nextRuningTime = startupEachDay.getEarliestDate(currentTime, 15, 45, 0);
		nextRuningTimeInLong = nextRuningTime.getTime().getTime();
		delay = nextRuningTimeInLong - currentTimeInLong;
		scheduledExecutorService.shutdown();
		
		scheduledExecutorService =  Executors.newScheduledThreadPool(10);
		
		
		scheduledExecutorService.scheduleAtFixedRate(startupEachDay, delay, period, TimeUnit.MILLISECONDS);
	}
}