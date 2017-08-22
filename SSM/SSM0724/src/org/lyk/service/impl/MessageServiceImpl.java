package org.lyk.service.impl;

import org.lyk.service.IMessageService;
import org.springframework.stereotype.Service;

@Service(value="messageServiceImpl")
public class MessageServiceImpl implements IMessageService
{
	@Override
	public boolean insert()
	{
		System.out.println("insert 方法正在执行");
		return false;
	}

}
