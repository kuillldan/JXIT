package org.lyk.factory;

import org.lyk.services.IMessageService;
import org.lyk.services.impl.MessageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceFactory
{
	@Bean
	public IMessageService getMessageServiceImpl()
	{
		return new MessageServiceImpl();
	}
}
