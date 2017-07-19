package cn.mldn.test;

import java.util.Iterator;
import java.util.List;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class TestNewsFindAll {
	public static void main(String[] args) throws Exception {
		// 代码之中，selectOne()方法直接转型
		List<News> all = MyBatisSessionFactory.getSession().selectList(
				"cn.mldn.vo.mapping.NewsNS.findAll");
		Iterator<News> iter = all.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		MyBatisSessionFactory.closeSession();
	}
}
