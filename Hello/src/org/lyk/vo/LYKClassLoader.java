package org.lyk.vo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class LYKClassLoader extends ClassLoader
{
	public static final String CLASSPATH = "C:" + File.separator + "D" + File.separator + "classes" + File.separator;
	public static final Integer BUFFERSIZE = 1024;//1024byte(1K) 
	public Class loadMyClass(String className) throws Exception
	{
		byte[] classInByte = this.loadClassFile(className);
		return super.defineClass(className, classInByte, 0, classInByte.length);
	}
	private byte[] loadClassFile(String className) throws Exception
	{
		byte[] classInByte = null;
		String classSimpleName = className.substring(className.lastIndexOf(".") + 1);
		InputStream is = new FileInputStream(new File(CLASSPATH + classSimpleName + ".class"));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[BUFFERSIZE];
		Integer len = 0 ;
		while((len = is.read(buffer, 0, BUFFERSIZE))>0)
		{
			bos.write(buffer, 0, len);
		}
		
		classInByte = bos.toByteArray();
		is.close();
		return classInByte;
	}
}