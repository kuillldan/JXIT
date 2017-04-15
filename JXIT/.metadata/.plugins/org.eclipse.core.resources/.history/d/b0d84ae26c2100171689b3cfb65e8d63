package org.lyk.vo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

class LYKClassLoader extends ClassLoader
{
	private static final String classPath = "C:" + File.separator + "D" + File.separator + "classes" + File.separator;
	
	public Class getMyClass(String className) throws Exception
	{
		byte[] classInBytes = this.getClassBytes(className);
		return super.defineClass(className, classInBytes, 0, classInBytes.length);
	}
	
	private byte[] getClassBytes(String className) throws Exception
	{
		byte[] classInBytes = null;
		InputStream is = new FileInputStream(classPath + className.substring(className.lastIndexOf(".")  + 1) + ".class");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		Integer len = 0;
		while((len = is.read(buffer,0,1024)) > 0)
		{
			bos.write(buffer, 0, len);
		}
		
		is.close();
		classInBytes = bos.toByteArray();
		
		return classInBytes;
	}
}