package timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TimePrinter implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("At the tone, the time is " + new Date());
	}

}
