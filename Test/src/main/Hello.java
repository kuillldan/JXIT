package main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import javafx.scene.control.Tab;
import factory.ServiceFactory;
import service.IDeptService;
import service.impl.DeptServiceImpl;
import utils.BeanOperator;
import utils.StringUtils;
import vo.Dept;
import vo.Employee;

class Person
{
	@SuppressWarnings("unchecked")
	public <T> T[] act(T[] array)
	{
		if (array == null || array.length < 1)
		{
			return null;
		}
		Set<T> set = new TreeSet<T>();
		for (int i = 0; i < array.length; i++)
		{
			set.add((T) array[i]);
		}
		int len = set.size();
		Class<?> clazz = array.getClass().getComponentType();
		T[] newArray = (T[]) set.toArray((T[]) Array.newInstance(clazz, len));
		return newArray;
	}
	
	public<T> void print()
	{
		System.out.println("Hello");
	}
}

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		Person  p = new Person();
		p.print();
		System.out.println("//Main done~~~");
	}

}