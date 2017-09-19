package timer;

import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimerTest
{
	public static void main(String[] args)
	{ 
		Timer t = new Timer(1000, event ->
		{
			System.out.println("At the tone, the time is " + new Date());
		});

		t.start();
		JOptionPane.showMessageDialog(null, "Quit Program?");

		System.exit(0);
	}
}
