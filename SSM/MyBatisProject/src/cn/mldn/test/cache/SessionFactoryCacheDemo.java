package cn.mldn.test.cache;

import org.apache.ibatis.session.SqlSession;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class SessionFactoryCacheDemo {
	public static void main(String[] args) {
		// 现在自己创建了一个SqlSession接口对象
		SqlSession sessionA = MyBatisSessionFactory.getSessionFactory().openSession() ;
		News voA = sessionA.selectOne("findById", 23) ;
		System.out.println(voA);
		sessionA.close();// 关闭SqlSession的同时写入缓存
		System.out.println("****************************");
		SqlSession sessionB = MyBatisSessionFactory.getSessionFactory().openSession() ;
		News voB = sessionB.selectOne("findById", 23) ;	// 同一个Session
		System.out.println(voB);
	}
}
 