package innerClass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date; 

import javax.swing.JOptionPane;
 

public class InnerClassTest
{
	public static void main(String[] args)
	{
		TalkingClock tc = new TalkingClock(3000,true);
		tc.start();
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}

class TalkingClock
{
	private Integer interval;
	private Boolean beep;
	public TalkingClock(Integer interval, Boolean beep)
	{
		super();
		this.interval = interval;
		this.beep = beep;
	}
	
	public TalkingClock()
	{
		// TODO Auto-generated constructor stub
	}
	
	public void start()
	{
//		class TimePrinter implements ActionListener
//		{	
//			@Override
//			public void actionPerformed(ActionEvent e)
//			{
//				System.out.println("Athe the tone, the time is " + new Date());
//				if(beep)
//				{
//					Toolkit.getDefaultToolkit().beep();
//				}
//			}
//		}
		 
		ActionListener listener = (e)->
		{
			System.out.println("Athe the tone, the time is " + new Date());
			if (TalkingClock.this.beep)
			{
				Toolkit.getDefaultToolkit().beep();
			}
		}; 
		//this.beep = false;
		 
		javax.swing.Timer timer = new javax.swing.Timer(interval, listener);
		timer.start();
	} 
}

class AAA
{}