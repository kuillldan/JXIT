package cn.mldn.test;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mldn.vo.News;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class TestNewsFindAllSplit {

	public static void main(String[] args) throws Exception {
		// 1、要读取整个项目的IBatis资源文件
		Reader reader = Resources.getResourceAsReader("ibatis.cfg.xml");
		// 2、要求加载所需要的文件内容，与SessionFactory的功能类似，返回的是一个操作对象
		// 这个返回的操作对象与Hibernate中的Session相同。
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		// 3、实现数据的增加操作
		int currentPage = 1;
		int lineSize = 2;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", "title");
		map.put("keyWord", "%%");
		// 计算过程要在程序中完成
		map.put("start", (currentPage - 1) * lineSize);
		map.put("lineSize", lineSize);
		List<News> all = sqlMap.queryForList("NewsNS.findAllSplit", map);
		System.out.println("总记录量："
				+ sqlMap.queryForObject("NewsNS.getAllCount", map));
		System.out.println(all);
		reader.close();
	}

}
