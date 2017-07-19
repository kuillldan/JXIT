package cn.mldn.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class TestNewsFindSplit {
	public static void main(String[] args) throws Exception {
		// 正常来讲，以下的几个参数是一定要传递的
		String keyWord = "";
		String column = "title";
		int currentPage = 1;
		int lineSize = 5;
		// 随后为了可以让MyBatis传递多个参数必须将其封装在Map集合之中
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", column);
		map.put("keyWord", "%" + keyWord + "%");
		map.put("start", (currentPage - 1) * lineSize);
		map.put("lineSize", lineSize);
		Integer count = MyBatisSessionFactory.getSession().selectOne(
				"getAllCount", map);
		System.out.println("总共的数据量：" + count);
		List<News> all = MyBatisSessionFactory.getSession().selectList(
				"cn.mldn.vo.mapping.NewsNS.findAllSplit", map);
		Iterator<News> iter = all.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		MyBatisSessionFactory.closeSession();
	}
}
