package lyk.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ServiceAspect 
{ 
	@Before(value="execution(* lyk.service.impl.*.*(..))")
	public void beforeService1()
	{
		System.out.println("【AOP before】写入日志...");
	}
	
	@Before(value="execution(* lyk.service.impl.*.*(..)) and args(vo)",argNames="vo")
	public void beforeService2(Object vo)
	{
		System.out.println("【AOP before】打开数据库连接..." + vo);
	}
	
	@After(value="execution(* lyk.service.impl.*.*(..))")
	public void afterService()
	{
		System.out.println("【AOP after】 关闭连接");
	}
	
	@AfterReturning(value="execution(* lyk.service.impl.*.*(..))",returning="retVal",argNames="retVal")
	public void callBack(Object retVal)
	{
		System.out.println("【AOP callBack】:" + retVal);
	}
	
	@AfterThrowing(value="execution(* lyk.service.impl.*.*(..))",throwing="e",argNames="e")
	public void exceptionHandler(Exception e)
	{
		System.out.println("【AOP exception handler】" + e);
	}
	
//	public boolean around(ProceedingJoinPoint pjp) throws Throwable
//	{
//		this.beforeService1();
//		Object[] args = pjp.getArgs();
//		this.beforeService2(args);
//		this.afterService();
//		Object retVal = pjp.proceed(pjp.getArgs());
//		this.callBack(retVal); 
//		return (Boolean)retVal;
//	}
}
