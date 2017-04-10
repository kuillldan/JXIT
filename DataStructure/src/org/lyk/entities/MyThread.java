package org.lyk.entities;

public class MyThread extends Thread
{
	private Book b1;
	private Book b2;
	public MyThread(Book b1, Book b2)
	{
		this.b1 = b1;
		this.b2 = b2;
	}
	
	
	@Override
	public void run()
	{
		synchronized (this.b1)
		{
			System.out.println(Thread.currentThread().getName() + " is operation on " + this.b1.getName());
			try
			{
				Thread.sleep(3000);
			} catch (Exception e)
			{
				// TODO: handle exception
			}
			
			
			synchronized (this.b2)
			{
				System.out.println(Thread.currentThread().getName() + " is operation on " + this.b2.getName());
			}
		}
	}
}
