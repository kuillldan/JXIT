package org.lyk.test;

import static org.junit.Assert.*;
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
		new NonStrictExpectations()
		{// 录制预期模拟行为
			{
				obj.hello("Zhangsan");
				returns("Hello Zhangsan");
				// 也可以使用：result = "Hello Zhangsan";
			}
		};
		assertEquals("Hello Zhangsan", obj.hello("Zhangsan"));// 调用测试方法
		new Verifications()
		{// 验证预期Mock行为被调用
			{
				obj.hello("Hello Zhangsan");
				times = 1;
			}
		};
	}
}
