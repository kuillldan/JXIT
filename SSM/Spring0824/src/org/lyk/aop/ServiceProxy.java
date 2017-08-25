package org.lyk.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Controller;

public class ServiceProxy
{
	public void before(Object param)
	{
		System.out.println("【切面】在服务层调用之前执行,参数:" + param);
	}

	public void after()
	{
		System.out.println("【切面】在服务层调用之后执行");
	}

	public void returned(Object retVal)
	{
		System.out.println("【切面】服务层调用返回值:" + retVal);
	}

	public boolean around(ProceedingJoinPoint pjp) throws Throwable
	{
		System.out.println("【切面】方法调用前，参数:" + Arrays.toString(pjp.getArgs()));
		Object retVal = pjp.proceed();
		System.out.println("【切面】方法调用后，返回值:" + retVal);
		return (boolean) retVal;

	}
}
