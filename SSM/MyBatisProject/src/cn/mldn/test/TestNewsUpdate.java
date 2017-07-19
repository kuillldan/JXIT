package cn.mldn.test;

import java.util.Date;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class TestNewsUpdate {
	public static void main(String[] args) throws Exception {
		News vo = new News();
		vo.setNid(3);
		vo.setTitle("周末愉快");
		vo.setContent("出来混总归是要还的，贪玩也总归是要还的。");
		vo.setPubdate(new Date());
		// 保存的时候要设置使用的SQL语句
		int len = MyBatisSessionFactory.getSession(true).insert(
				"cn.mldn.vo.mapping.NewsNS.doUpdate", vo);
		System.out.println("操作影响的数据行数：" + len);
		MyBatisSessionFactory.closeSession();
	}
}
