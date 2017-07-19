package cn.mldn.test;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class TestNewsFindById {
	public static void main(String[] args) throws Exception {
		// 代码之中，selectOne()方法直接转型
		News vo = MyBatisSessionFactory.getSession().selectOne(
				"cn.mldn.vo.mapping.NewsNS.findById", 25);
		System.out.println(vo);
		MyBatisSessionFactory.closeSession();
	}
}
