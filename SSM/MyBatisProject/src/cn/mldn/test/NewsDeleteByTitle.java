package cn.mldn.test;

import cn.mldn.util.MyBatisSessionFactory;

public class NewsDeleteByTitle {
	public static void main(String[] args) throws Exception {
	int len = MyBatisSessionFactory.getSession().delete(
			"cn.mldn.vo.mapping.NewsNS.doRemoveByTitle", "周末愉快");
		MyBatisSessionFactory.getSession().commit();
		System.out.println("操作影响的数据行数：" + len);
		MyBatisSessionFactory.closeSession();
	}
}
