package org.lyk.main;

import java.sql.Timestamp;

import org.apache.ibatis.session.SqlSession;
import org.lyk.factory.MyBatisSessionFactory;
import org.lyk.vo.TimeDemo;

public class Hello
{

	public static void main(String[] args)
	{
		 SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
		 
		 
//		 TimeDemo timeDemo = new TimeDemo();
//		 timeDemo.setId(3);
//		 timeDemo.setInfo("ccc");
//		 timeDemo.setCurrent(new Timestamp(System.currentTimeMillis()));
//		 System.out.println(sqlSession.insert("org.lyk.vo.mapping.TimeDemoNS.doUpdate",timeDemo));
//		 sqlSession.commit();
		 
		 TimeDemo timeDemo = sqlSession.selectOne("org.lyk.vo.mapping.TimeDemoNS.findById", 3);
		 System.out.println(timeDemo.getCurrent().getTime());//1500880726000
		 			//1500881225000
		 
		 sqlSession.close();
		 System.out.println("//Main done");
	}
}
