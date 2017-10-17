package org.lyk.services.impl;

import org.lyk.services.IMessageService;
import org.springframework.stereotype.Service;

@Service("messageServiceImpl")
public class MessageServiceImpl implements IMessageService
{
	@Override
	public String getInto()
	{
		return "今天是个好天气";
	}

}
