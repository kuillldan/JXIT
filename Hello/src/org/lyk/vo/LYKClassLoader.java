package org.lyk.vo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class LYKClassLoader extends ClassLoader
{
	public Class getMyClass(String className) throws Exception
	{
		byte[] classInBytes = this.getClassBytes(className);
		return super.defineClass(className, classInBytes, 0, classInBytes.length);
	}
	
	public byte[] getClassBytes(String className) throws Exception
	{
		byte[] classInBytes = null;
		InputStream is = new FileInputStream("C:" + File.separator + "D" + File.separator + className.substring(className.lastIndexOf(".")  + 1) + ".class");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int bytesReaded = 0;
		while((bytesReaded = is.read(buffer)) > 0)
		{
			bos.write(buffer, 0, bytesReaded);
		}
		is.close();
		bos.close();
		classInBytes = bos.toByteArray();
		return classInBytes;
	}
}
