package org.lyk.test;

import static org.junit.Assert.*;
import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;

import org.junit.Test;
import org.lyk.vo.MyObject;

public class TestHello
{
	@Mocked
	// 用@Mocked标注的对象，不需要赋值，jmockit自动mock
	MyObject obj;

	@Test
	public void testHello()
	{
		new Expectations()
		{// 录制预期模拟行为
			{
				obj.hello("Zhangsan");
				// returns("Hello Zhangsan");
				obj.hello("Zhangsan1");
				result = "Hello Zhangsan";
			}
		};

		obj.hello("Zhangsan");
		obj.hello("Zhangsan1");
		 
	}
}
