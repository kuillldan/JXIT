package main;

import java.awt.print.Book;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;



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
	public String className();
}

@ClassInfo(className="main.IPhone")
class MessageFactory
{
	public static Message getMessageInstance() throws Exception
	{
		Class<?> helloClass = MessageFactory.class;
		ClassInfo classInfoAnnotation = helloClass.getAnnotation(ClassInfo.class);
		String className = classInfoAnnotation.className();
		Class<?> cls = Class.forName(className);
		Object obj = cls.newInstance();
		Message message = (Message)obj;
		return message;
	}
}



public class Hello
{
	public static void main(String[] args) throws Exception
	{
		Class<?> bookClass = Class.forName("vo.Book");
		Constructor<?> cons = bookClass.getConstructor();
		Object obj = cons.newInstance();
		System.out.println(obj); 
	}
}