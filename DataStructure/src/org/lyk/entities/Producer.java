package org.lyk.entities;

public class Producer implements Runnable
{
	private Info info;
	private Object lock;

	public Producer(Info info,Object lock)
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
				if (i % 2 == 0)
				{
					this.info.setInfo("������", "��ѧ��һö");
				} else
				{
					this.info.setInfo("������", "�ɰ����ȳ�");
				}
			}
		}
	}

}
