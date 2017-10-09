package org.lyk.main;

import org.lyk.vo.MyTestBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Hello world!
 *
 */
public class App
{
	@SuppressWarnings("deprecation")
	public static void main(String[] args)
	{
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		MyTestBean bean = (MyTestBean)bf.getBean("myTestBean");
		System.out.println(bean.getTestStr());
		DefaultListableBeanFactory dd;
	}
}
