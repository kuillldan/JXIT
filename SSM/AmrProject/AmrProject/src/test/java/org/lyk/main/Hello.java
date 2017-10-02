package org.lyk.main;

import java.util.Arrays;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lyk.constant.CommonConstant;
import org.lyk.dao.IEmpDAO;
import org.lyk.dao.impl.EmpDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Hello
{
	public static String getDebugInfo()
	{
		StackTraceElement[] lvStacks = Thread.currentThread().getStackTrace();
		return "Class Name：" + lvStacks[2].getClassName() + ",Function Name：" + lvStacks[2].getMethodName() + ",Line："
				+ lvStacks[2].getLineNumber() + "\n";
	}

	public static void main(String[] args)
	{
//		CommonConstant.LOGGER.debug(getDebugInfo() + "这是一行调试代码.应该显示是Hello.java的23行.");
//		System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
		AAA();
	}
	
	public static void AAA()
	{
		System.out.println(getDebugInfo());
	}
}
