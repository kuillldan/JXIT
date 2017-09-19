package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import interfaces.Employee;

public class TraceHandlerTest
{
	public static void main(String[] args)
	{
		Employee a = new Employee("", 22D);
		InvocationHandler h = new TraceHandler(a);
		Object proxyObject = Proxy.newProxyInstance(a.getClass().getClassLoader(), a.getClass().getInterfaces(), h);
		Class<?> proxyClass = Proxy.getProxyClass(a.getClass().getClassLoader(), a.getClass().getInterfaces());
		System.out.println(proxyClass);
		System.out.println(Arrays.toString(proxyClass.getInterfaces()));
	}
}
