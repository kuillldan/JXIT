package org.lyk.service.impl;

import org.lyk.service.IMessageService;

//
public class MessageServiceImpl implements IMessageService
{

	@Override
	public String getInfo()
	{
		return "www.google.com";
	}

	@Override
	public boolean remove(String mid)
	{
		System.out.println("[业务层]执行删除调用，删除的数据ID=" +mid);
		return false;
	}

}
