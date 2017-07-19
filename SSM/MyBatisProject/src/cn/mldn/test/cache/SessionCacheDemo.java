package cn.mldn.test.cache;

import org.apache.ibatis.session.SqlSession;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class SessionCacheDemo {
	public static void main(String[] args) {
		// 现在自己创建了一个SqlSession接口对象
		SqlSession session = MyBatisSessionFactory.getSessionFactory().openSession() ;
		News voA = session.selectOne("findById", 23) ;
		System.out.println(voA);
		session.clearCache(); // 进行了修改的操作，那么这个时候一定是需要同步
		System.out.println("****************************");
		News voB = session.selectOne("findById", 23) ;	// 同一个Session
		System.out.println(voB);
	}
}
