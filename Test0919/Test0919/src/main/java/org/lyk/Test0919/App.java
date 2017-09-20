package org.lyk.Test0919;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import com.sun.org.apache.bcel.internal.generic.NEW;

import ch05.Employee;
import ch05.Manager;
import ch05.Person;
import ch05.Student;

/**
 * Hello world!
 *
 */

class A<T>
{
	private T name;
	private T address;
	private Integer code;

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}

	public T getName()
	{
		return name;
	}

	public void setName(T name)
	{
		this.name = name;
	}

	public T getAddress()
	{
		return address;
	}

	public void setAddress(T address)
	{
		this.address = address;
	}

	@Override
	public String toString()
	{
		return super.getClass().getName() + " [name=" + name + ", address=" + address + ", code=" + code + "]";
	}
}

enum Color
{
	RED, BLUE
}

public class App
{
	public static void main(String[] args) throws Exception
	{
		A a = new A();
		Class<?> c1 = A.class;
		Class<?> c2 = a.getClass();
		Class<?> c3 = Class.forName("org.lyk.Test0919.A");
		System.out.println(c1 == c2);
		System.out.println(c2 == c3);
		System.out.println(c3 == c1);
	}

	public static void fun(A<?> a)
	{
		a.setCode(22);
	}
}
