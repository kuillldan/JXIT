package org.lyk.entities;

public class Comsumer implements Runnable
{
	private Info info;
	private Object lock;

	public Comsumer(Info info,Object lock)
	{
		this.info = info;
		this.lock = lock;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 100; i++)
		{
			synchronized (this.lock)
			{
				System.out.println(this.info.getInfo());
			}
		}
	}
}
