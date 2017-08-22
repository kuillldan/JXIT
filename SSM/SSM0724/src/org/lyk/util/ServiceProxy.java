package org.lyk.util;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceProxy
{
	@Before(value="execution(* org.lyk.service.impl..*.*(..))")
	public void beforeInvoke()
	{
		System.out.println("方法调用前进行处理");
	}
	
	@After(value="execution(* org.lyk.service.impl..*.*(..))")
	public void afterInvoke()
	{
		System.out.println("方法调用结束后进行处理");
	}
}
