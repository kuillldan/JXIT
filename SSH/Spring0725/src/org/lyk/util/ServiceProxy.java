package org.lyk.util;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceProxy
{
	@Before(value="execution(public * org.lyk.service.impl..*.*(..)) and args(param)",argNames="param")
	public void beforeInvoke(Object param)
	{
		System.out.println("[ServiceProxy-BEFORE111] 在业务方法执行前调用,参数:" + param);
	}
	
	@After(value="execution(public * org.lyk.service.impl..*.*(..))")
	public void afterInvoke()
	{
		System.out.println("[ServiceProxy-AFTER] 在业务方法执行后调用");
	}
	
	public void returnInvoke(Object val)
	{
		System.out.println("[ServiceProxy-RETURNING] 返回值:" +  val);
	}
}
