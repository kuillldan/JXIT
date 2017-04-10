package org.lyk.entities;

public class Info
{
	private Object lock;
	private String name;
	private String desc;
	private boolean readyToGet = false;

	public Info(String name, String desc)
	{
		super();
		this.name = name;
		this.desc = desc;
	}
	public void setLock(Object lock)
	{
		this.lock = lock;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public  void setInfo(String name, String desc)
	{
		try
		{
			if(this.readyToGet == true)
				this.lock.wait();
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		
		this.setName(name);
		try
		{
			Thread.sleep(100);
		} catch (Exception ignore)
		{
		}
		this.setDesc(desc);
		this.readyToGet = true;
		this.lock.notifyAll();
	}

	public  String getInfo()
	{
		try
		{
			if(this.readyToGet == false)
				this.lock.wait();
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		
		String name = this.getName();
		String desc = null;
		try
		{
			desc = this.getDesc();
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		this.readyToGet = false;
		this.lock.notifyAll();
		return name + "->" + desc;
		
	}
}
