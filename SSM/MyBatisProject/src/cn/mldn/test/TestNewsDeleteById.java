package cn.mldn.test;

import cn.mldn.util.MyBatisSessionFactory;

public class TestNewsDeleteById {
	public static void main(String[] args) throws Exception {
		int len = MyBatisSessionFactory.getSession().delete(
				"cn.mldn.vo.mapping.NewsNS.doRemove", 5);
		MyBatisSessionFactory.getSession().commit();
		System.out.println("操作影响的数据行数：" + len);
		MyBatisSessionFactory.closeSession();
	}
}
