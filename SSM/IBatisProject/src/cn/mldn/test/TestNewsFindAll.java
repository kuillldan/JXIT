package cn.mldn.test;

import java.io.Reader;
import java.util.List;

import cn.mldn.vo.News;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class TestNewsFindAll {

	public static void main(String[] args) throws Exception {
		// 1、要读取整个项目的IBatis资源文件
		Reader reader = Resources.getResourceAsReader("ibatis.cfg.xml") ;
		// 2、要求加载所需要的文件内容，与SessionFactory的功能类似，返回的是一个操作对象
		// 这个返回的操作对象与Hibernate中的Session相同。
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader) ;
		// 3、实现数据的增加操作
		List<News> all = sqlMap.queryForList("NewsNS.findAll") ;
		System.out.println(all);
		reader.close(); 
	}

}
