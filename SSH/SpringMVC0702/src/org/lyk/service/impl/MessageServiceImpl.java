package org.lyk.service.impl;

import org.apache.log4j.Logger;
import org.lyk.service.IMessageService;

public class MessageServiceImpl implements IMessageService
{

	@Override
	public boolean remove(String mid)
	{
		System.out.println("【业务层】执行删除调用，删除的数据ID=" + mid);
		return false;
	}

}
