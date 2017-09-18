package timer;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimerTest
{
	public static void main(String[] args)
	{
		ActionListener listener = new TimePrinter();
		Timer t = new Timer(1000, listener);
		t.start();
		JOptionPane.showMessageDialog(null, "Quit Program?");
		
		System.exit(0);
	}
}
