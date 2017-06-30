package org.lyk.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataConnectionHandler
{
	@Before(value="execution(* org.lyk.service.impl.*.*(..)) and args(param)",argNames="param")
	public void openConnection(Object obj)
	{
		System.out.println("数据库连接已打开");
		System.out.println("传入的参数:" + obj);
	}
	
	@After("execution(* org.lyk.service.impl.*.*(..))")
	public void closeConnection()
	{
		System.out.println("数据库连接已关闭");
	}
	
	@AfterReturning(value="execution(* org.lyk.service.impl.*.*(..))",argNames="retVal",returning="retVal")
	public void afterServiceDone(Object retVal)
	{
		System.out.println("【参数】" + retVal);
	}
	
	@AfterThrowing(value="execution(* org.lyk.service.impl.*.*(..))",argNames="e",throwing="e")
	public void afterExceptionThrows(Exception e)
	{
		System.out.println("发现异常:" + e.getMessage());
	}
	
	public boolean thisOneForAll(ProceedingJoinPoint point) throws Throwable
	{
		System.out.println("【传入参数】:" + Arrays.toString(point.getArgs()));
		Object retVal  = point.proceed();
		System.out.println("【返回参数】:" + retVal);
		return true;
	}
}
