package main;

import java.lang.annotation.*;
import java.lang.reflect.*;
import utils.StringUtils;

interface Message
{
	public void showMessage(String msg);
}

class IPhone implements Message
{

	@Override
	public void showMessage(String msg)
	{
		System.out.println("IPhone消息:" + msg);
	}
}

class Android implements Message
{

	@Override
	public void showMessage(String msg)
	{
		System.out.println("Android消息:" + msg);
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface ClassInfo
{
	public String value();
}

@ClassInfo("main.Android")
class MessageFactory
{
	public static Message getInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class<?> messageFactoryClass = MessageFactory.class;
		ClassInfo classInfoAnnotation = messageFactoryClass.getAnnotation(ClassInfo.class);
		if (classInfoAnnotation == null)
			return null;
		String className = classInfoAnnotation.value();
		if (StringUtils.isEmpty(className))
			return null;
		Class<?> realObjectClass = Class.forName(className);
		Object realObject = realObjectClass.newInstance();
		return (Message) realObject;
	}
}

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		Class<?> bookClass = Class.forName("vo.Book");
		Object obj = bookClass.newInstance();
		Method setter = bookClass.getDeclaredMethod("setTitle", String.class);
		setter.invoke(obj, "JAVA彻底变成死相");
		System.out.println(obj);
		System.out.println("//Main done~~~");
	}

	public static void printDeclaredMethods(String className) throws ClassNotFoundException
	{
		Class<?> bookClass = Class.forName(className);
		Method[] allMethods = bookClass.getDeclaredMethods();
		for (int i = 0; i < allMethods.length; i++)
		{
			Method method = allMethods[i];
			StringBuffer methodString = new StringBuffer();
			methodString.append(Modifier.toString(method.getModifiers())).append(" ");
			Class<?> returnType = method.getReturnType();
			methodString.append(returnType.getSimpleName()).append(" ");
			methodString.append(method.getName());
			methodString.append("(");

			Class<?>[] parameterTypes = method.getParameterTypes();
			for (int j = 0; j < parameterTypes.length; j++)
			{
				methodString.append(parameterTypes[j].getSimpleName()).append(" args").append(j);

				if (j < parameterTypes.length - 1)
				{
					methodString.append(", ");
				}
			}

			methodString.append(")");

			Class<?>[] allExceptionTypes = method.getExceptionTypes();
			if (allExceptionTypes != null && allExceptionTypes.length > 0)
			{
				methodString.append("throws ");
				for (int k = 0; k < allExceptionTypes.length; k++)
				{
					methodString.append(allExceptionTypes[i].getSimpleName());
					if (k < allExceptionTypes.length - 1)
					{
						methodString.append(", ");
					}
				}
			}

			System.out.println(methodString);
		}
	}

	public static void printConstructors(String className) throws ClassNotFoundException
	{
		Class<?> bookClass = Class.forName(className);
		Constructor<?>[] allConstructors = bookClass.getConstructors();
		for (int i = 0; i < allConstructors.length; i++)
		{
			Constructor<?> cons = allConstructors[i];
			StringBuffer conString = new StringBuffer();
			conString.append(Modifier.toString(cons.getModifiers())).append(" ");
			conString.append(cons.getName()).append("(");
			Class<?>[] allParameterTypes = cons.getParameterTypes();
			for (int j = 0; j < allParameterTypes.length; j++)
			{
				conString.append(allParameterTypes[j].getSimpleName()).append(" args").append(j);
				if (j < allParameterTypes.length - 1)
				{
					conString.append(", ");
				}
			}

			conString.append(")");

			Class<?>[] allExceptions = cons.getExceptionTypes();
			if (allExceptions != null && allExceptions.length > 0)
			{
				conString.append("throws ");
				for (int k = 0; k < allExceptions.length; k++)
				{
					conString.append(allExceptions[k].getSimpleName());
					if (k < allExceptions.length - 1)
					{
						conString.append(", ");
					}
				}
			}

			System.out.println(conString);
		}
	}
}