package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import vo.Member;

public class Main
{	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ParseException
	{
		//取的配置文件信息
		Configuration configuration = new Configuration();
		//读取默认资源
		
		configuration.configure();
		ServiceRegistry serviceRegistry  = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory  sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		
		Member member = new Member();
		member.setAge(33);
		member.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1987-10-18"));
		member.setMid("21591924");
		member.setName("yuankui");
		member.setNote("a good guy");
		member.setSalary(9999.0);
		Transaction trans = session.beginTransaction();
		session.save(member);
		trans.commit();
		session.close();
		System.out.println("//Main ~~~");
	}

}
