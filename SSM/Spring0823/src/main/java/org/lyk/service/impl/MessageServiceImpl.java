package org.lyk.service.impl;
// org.lyk.service.impl.MessageServiceImpl
import org.lyk.service.IMessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService
{
	@Override
	public String getInfo()
	{
		
		return "Hello Shit";
	}

}
