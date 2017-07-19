package cn.mldn.test;

import java.util.Date;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class TestNewsInsertFactory {
	public static void main(String[] args) throws Exception {
		News vo = new News();
		vo.setTitle("欢迎访问www.mldn.cn");
		vo.setContent("世界和平，大家快乐！ - " + System.currentTimeMillis());
		vo.setPubdate(new Date());
		// 保存的时候要设置使用的SQL语句
		int len = MyBatisSessionFactory.getSession().insert(
				"cn.mldn.vo.mapping.NewsNS.doCreate", vo);
		MyBatisSessionFactory.getSession().commit();
		System.out.println("操作影响的数据行数：" + len);
		System.out.println("当前的id：" + vo.getNid());
		MyBatisSessionFactory.closeSession();
	}
}
