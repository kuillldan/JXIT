package org.lyk.service.impl;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect 
public class ServiceProxy
{
	@Before(value="execution(public * org.lyk.service.impl..*.*(..)) and args(param)",argNames="param")
	public void beforeInvoke(Object param)
	{
		System.out.println("【ServiceProxy - BEFORE】在业务方法执行之前进行调用:" + param);
	}
	
	@After(value="execution(public * org.lyk.service.impl..*.*(..))")
	public void afterInvoke()
	{
		System.out.println("【ServiceProxy - AFTER】在业务方法执行之后进行调用");
	}
	
	@AfterReturning(value="execution(public * org.lyk.service.impl..*.*(..))",returning="retVal",argNames="retVal")
	public void returnInvoke(Object retVal)
	{
		System.out.println("【ServiceProxy - RETURN】返回值:" + retVal);
	}
	
	public Object aroundInvoke(ProceedingJoinPoint pjp) throws Throwable
	{
		System.out.println("【ServiceProxy - BEFORE】环绕调用之前");
		Object[] args = pjp.getArgs();
		for(Object arg : args)
		{
			System.out.println(arg);
		}
		
		Object retVal = pjp.proceed(pjp.getArgs());
		System.out.println("【ServiceProxy - AFTER】环绕调用之后,返回值:" + retVal);
		return true;
	}
}
