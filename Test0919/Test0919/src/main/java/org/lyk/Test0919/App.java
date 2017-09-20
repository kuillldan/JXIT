package org.lyk.Test0919;

import java.util.function.Function;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.sun.org.apache.bcel.internal.generic.NEW;

import ch05.Employee;
import ch05.Manager;
import ch05.Person;
import ch05.Student;

/**
 * Hello world!
 *
 */

class A<T> implements Comparable<T>
{
	
	public void showName(Supplier<String> s)
	{
		s.get();
	}

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

	@Override
	public int compareTo(T o)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}

interface Supplier<T>
{
	T get();
}

enum Color
{
	RED, BLUE
}

class TimePrinter implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("At the tone, the time is " + new Date());
		Toolkit.getDefaultToolkit().beep();
	}
}

class MyThread implements Runnable
{
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
	}
}

interface IntConsumer
{
	void accept(int value);
}



public class App
{
	public static void main(String[] args) throws Exception
	{
		Employee e = new Employee();
		
		System.out.println(e.getSalary());
		
		System.out.println(e.getName());
	}
	
	public static void repeat(int n, IntConsumer action)
	{
		for(int i = 0; i < n; i++)
		{
			action.accept(i);
		}
	}

	public static void fun(Supplier<String> s)
	{
		System.out.println(s.get());
	}
}
