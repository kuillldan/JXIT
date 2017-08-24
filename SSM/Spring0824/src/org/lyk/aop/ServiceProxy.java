package org.lyk.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Controller;
 
public class ServiceProxy
{
	public void before()
	{
		System.out.println("【切面】在服务层调用之前执行");
	}
	
	public void after()
	{
		System.out.println("【切面】在服务层调用之后执行");
	}
}
